package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.bo.SpuBo;
import com.leyou.common.dto.CartDataTransferObject;
import com.leyou.common.pojo.PageResult;
import com.leyou.mapper.SkuMapper;
import com.leyou.mapper.SpuDetailMapper;
import com.leyou.mapper.SpuMapper;
import com.leyou.mapper.StockMapper;
import com.leyou.pojo.Sku;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuDetail;
import com.leyou.pojo.Stock;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows){
        //添加查询条件
        //添加过滤条件
        //添加分页
        PageHelper.startPage(page, rows);
        //执行查询,获取spu集合
        //spu集合-->spuBo集合
        List<SpuBo> spuBos = spuMapper.searchSpus(key, saleable);

        //返回PageResult<SpuBo>
        PageInfo<SpuBo> pageInfoSpuBos = new PageInfo<>(spuBos);
        return new PageResult<SpuBo>(pageInfoSpuBos.getTotal(), pageInfoSpuBos.getList());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void saveGoods(SpuBo spuBo){
        Date currentDate = new Date();
        //设置Spu默认值
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(currentDate);
        spuBo.setLastUpdateTime(currentDate);
        spuMapper.addSpu(spuBo);

        Long spuId = spuBo.getId();
        //SpuDetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuId);
        spuDetailMapper.addSpuDetail(spuDetail);

        //Sku
        spuBo.getSkus().forEach(skuItem -> {
            skuItem.setId(null);
            skuItem.setSpuId(spuId);
            skuItem.setCreateTime(currentDate);
            skuItem.setLastUpdateTime(currentDate);
            skuMapper.addSku(skuItem);
            Long skuId = skuItem.getId();

            Stock stock = new Stock();
            stock.setSkuId(skuId);
            stock.setStock(skuItem.getStock());
            stockMapper.addStock(stock);
        });
        //发送消息到rabbitmq
        sendMessage("insert", spuId);
    }

    public SpuDetail querySpuDetailBySpuId(Long spuId){
        return spuDetailMapper.querySpuDetailBySpuId(spuId);
    }

    public List<Sku> querySkusBySpuId(Long spuId){
        return skuMapper.querySkusBySpuId(spuId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void updateGoods(SpuBo spuBo){
        Long spuId = spuBo.getId();
        Date currentDate = new Date();
        //查询旧的skus
        List<Sku> oldSkus = skuMapper.querySkusBySpuId(spuId);
        if(!CollectionUtils.isEmpty(oldSkus)){
            oldSkus.forEach(skuItem -> {
                //删除旧的skus关联库存
                Long skuId = skuItem.getId();
                stockMapper.deleteStock(skuId);
            });
            //删除旧的skus
            skuMapper.deleteSkus(spuId);
        }

        //更新spu和spuDetail
        spuBo.setLastUpdateTime(currentDate);
        //spuBo.setCreateTime(null);
        //spuBo.setValid(null);
        //spuBo.setSaleable(null);
        spuMapper.modifySpu(spuBo);

        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetailMapper.modifySpuDetail(spuDetail);

        //新增skus及库存
        spuBo.getSkus().forEach(newSkuIem -> {
            newSkuIem.setId(null);
            newSkuIem.setSpuId(spuId);
            newSkuIem.setCreateTime(currentDate);
            newSkuIem.setLastUpdateTime(currentDate);
            skuMapper.addSku(newSkuIem);
            Long newSkuId = newSkuIem.getId();

            Stock newStock = new Stock();
            newStock.setSkuId(newSkuId);
            newStock.setStock(newSkuIem.getStock());
            stockMapper.addStock(newStock);
        });

        //发送消息到rabbitmq
        sendMessage("update", spuId);
    }

    public Spu querySpuById(Long id){
        return spuMapper.querySpuById(id);
    }

    //新增修改商品使用rabbitmq发送消息
    private void sendMessage(String type, Long spuId){
        try {
            amqpTemplate.convertAndSend("item." + type, spuId);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    public Sku queryBySkuId(Long skuId){
        return skuMapper.querySkuById(skuId);
    }

    public List<Sku> querySkuBySkuIds(List<Long> skuIds){
        return skuMapper.querySkuBySkuIds(skuIds);
    }

    @Transactional
    public void decreaseStock(List<CartDataTransferObject> cartDtos){
        for (CartDataTransferObject cartItem : cartDtos){
            int result = stockMapper.decreaseStock(cartItem.getSkuId(), cartItem.getNum());
            System.out.println("减库存返回结果: " + result);
        }
    }

}
