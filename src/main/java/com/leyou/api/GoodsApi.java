package com.leyou.api;

import com.leyou.bo.SpuBo;
import com.leyou.common.dto.CartDataTransferObject;
import com.leyou.common.pojo.PageResult;
import com.leyou.pojo.Sku;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GoodsApi {

    //分页查询商品
    @GetMapping("/spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "page" , defaultValue = "1")Integer page,
            @RequestParam(value = "rows" , defaultValue = "5")Integer rows,
            @RequestParam(value = "saleable" , required = false)Boolean saleable,
            @RequestParam(value = "key" , required = false)String key);

    //根据spuId查询商品详情
    @RequestMapping("spu/detail/{id}")
    public SpuDetail querySpuDetailById(@PathVariable("id") Long spuId);

    //根据spuId查询Skus
    @GetMapping("sku/list")
    public List<Sku> querySkusBySpuId(@RequestParam("id") Long id);

    @RequestMapping("{id}")
    public Spu querySpuById(@PathVariable("id") Long id);

    @RequestMapping("sku/{skuId}")
    public Sku querySkuById(@PathVariable("skuId")Long skuId);

    @RequestMapping("sku/listSkuIds")
    public List<Sku> querySkuBySkuIds(@RequestParam("skuIds") List<Long> skuIds);

    @PostMapping("stock/decrease")
    public void decreaseStock(@RequestBody List<CartDataTransferObject> cartDTOs);

}
