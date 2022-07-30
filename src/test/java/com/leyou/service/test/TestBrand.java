package com.leyou.service.test;

import com.leyou.LeyouItemServiceApplication;
import com.leyou.bo.SpuBo;
import com.leyou.common.dto.CartDataTransferObject;
import com.leyou.pojo.*;
import com.leyou.service.BrandService;
import com.leyou.service.CategoryService;
import com.leyou.service.GoodsService;
import com.leyou.service.SpecificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = LeyouItemServiceApplication.class)
@RunWith(SpringRunner.class)
public class TestBrand {

    @Autowired
    private BrandService brandService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpecificationService specificationService;

    @Test
    public void insertBrandAndCategory(){
        Brand brand = new Brand();
        brand.setName("测试数据");
        brand.setLetter('C');
        brand.setImage("http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg");

        List<Long> cids = new ArrayList<Long>();
        cids.add(8L);
        cids.add(9L);
        System.out.println(cids.size());
        brandService.addBrandRelated(brand, cids);
    }

    /*
    {
        "brandId": 10640,
            "title": "黑马",
            "subTitle": "黑马送苹果",
            "spuDetail": {
        "packingList": "纸壳包装",
                "afterService": "即坏即换",
                "description": "<p>填这儿试试<img src=\"http://image.leyou.com/group1/M00/00/00/wKgBaGF1aq6AHzU6AAJPutepJzI494.jpg\"></p>",
                "genericSpec": "{\"1\":\"黑马\",\"2\":\"黑马001\",\"3\":\"2021\",\"5\":\"500\",\"6\":\"泡沫\",\"7\":\"安卓\",\"8\":\"酷睿\",\"9\":\"I5\",\"10\":\"3核\",\"11\":\"2048\",\"14\":\"1000\",\"15\":\"1000\",\"16\":\"1000\",\"17\":\"1000\",\"18\":\"2000\"}",
                "specialSpec": "{\"4\":[\"狸花\",\"橘色\"],\"12\":[\"6G\"],\"13\":[\"128G\"]}"
    },
        "cid1": 74,
            "cid2": 75,
            "cid3": 76,
            "skus": [
                {
                    "price": 1000,
                        "stock": "100",
                        "indexes": "0_0_0",
                        "enable": true,
                        "title": "黑马 狸花 6G 128G",
                        "images": "",
                        "ownSpec": "{\"4\":\"狸花\",\"12\":\"6G\",\"13\":\"128G\"}"
                },
                {
                    "price": 1100,
                        "stock": "90",
                        "indexes": "1_0_0",
                        "enable": true,
                        "title": "黑马 橘色 6G 128G",
                        "images": "",
                        "ownSpec": "{\"4\":\"橘色\",\"12\":\"6G\",\"13\":\"128G\"}"
                }
          ]
    }*/

    @Test
    public void testGoodsAdd(){
        SpuBo spuBo = new SpuBo();
        spuBo.setBrandId(10640L);
        spuBo.setTitle("黑马");
        spuBo.setSubTitle("黑马送苹果");
        spuBo.setCid1(74L);
        spuBo.setCid2(75L);
        spuBo.setCid3(76L);

        SpuDetail spuDetail = new SpuDetail();
        spuDetail.setDescription("<p>填这儿试试<img src=\\\"http://image.leyou.com/group1/M00/00/00/wKgBaGF1aq6AHzU6AAJPutepJzI494.jpg\\\"></p>");
        spuDetail.setGenericSpec("{\"1\":\"黑马\",\"2\":\"黑马001\",\"3\":\"2021\",\"5\":\"500\",\"6\":\"泡沫\",\"7\":\"安卓\",\"8\":\"酷睿\",\"9\":\"I5\",\"10\":\"3核\",\"11\":\"2048\",\"14\":\"1000\",\"15\":\"1000\",\"16\":\"1000\",\"17\":\"1000\",\"18\":\"2000\"}");
        spuDetail.setSpecialSpec("{\"4\":[\"狸花\",\"橘色\"],\"12\":[\"6G\"],\"13\":[\"128G\"]}");
        spuDetail.setPackingList("\"纸壳包装\"");
        spuDetail.setAfterService("即坏即换");
        spuBo.setSpuDetail(spuDetail);
//        {
//            "price": 1000,
//                "stock": "100",
//                "indexes": "0_0_0",
//                "enable": true,
//                "title": "黑马 狸花 6G 128G",
//                "images": "",
//                "ownSpec": "{\"4\":\"狸花\",\"12\":\"6G\",\"13\":\"128G\"}"
//        },
        Sku sku1 = new Sku();
        sku1.setPrice(1000L);
        sku1.setIndexes("0_0_0");
        sku1.setEnable(true);
        sku1.setTitle("黑马 狸花 6G 128G");
        sku1.setImages("http://image.leyou.com/group1/M00/00/00/wKgBaGF1ayOAHs25AAPl1x3oVsI123.jpg");
        sku1.setOwnSpec("{\"4\":\"狸花\",\"12\":\"6G\",\"13\":\"128G\"}");
        sku1.setStock(100);
//        Stock k1 = new Stock();
//        k1.setStock(100);

//        {
//            "price": 1100,
//                "stock": "90",
//                "indexes": "1_0_0",
//                "enable": true,
//                "title": "黑马 橘色 6G 128G",
//                "images": "",
//                "ownSpec": "{\"4\":\"橘色\",\"12\":\"6G\",\"13\":\"128G\"}"
//        }
        Sku sku2 = new Sku();
        sku2.setPrice(1100L);
        sku2.setIndexes("1_0_0");
        sku2.setEnable(true);
        sku2.setTitle("黑马 橘色 6G 128G");
        sku2.setImages("http://image.leyou.com/group1/M00/00/00/wKgBaGF1azWADL0kAAO3Nhnxsds472.jpg");
        sku2.setOwnSpec("{\\\"4\\\":\\\"橘色\\\",\\\"12\\\":\\\"6G\\\",\\\"13\\\":\\\"128G\\\"}");
        sku2.setStock(90);
        //Stock k2 = new Stock();
       // k2.setStock(90);

        List<Sku> skus = new ArrayList<>();
        skus.add(sku1);
        skus.add(sku2);
        spuBo.setSkus(skus);
        goodsService.saveGoods(spuBo);
    }

    @Test
    public void searchSkus(){
        List<Sku> skus = goodsService.querySkusBySpuId(2L);
        for(Sku item : skus){
            System.out.println(item.getTitle() + "\t" + item.getStock());
        }
    }

    @Test
    public void searchCategoryNames(){
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        List<String> categoryNames = categoryService.getCategoryNames(ids);
        categoryNames.forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void testQueryBySpuId(){
        Spu spu =  goodsService.querySpuById(2L);
        System.out.println(spu.getTitle());
    }

    @Test
    public void testQueryGroupsWithParams(){
        List<SpecGroup> specGroups = specificationService.queryGroupsWithParams(76L);
        specGroups.forEach(group -> {
            System.out.println(group.getParams().size());
        });
    }

    @Test
    public void testQuerySpecParams(){
        List<SpecParam> specParamps = specificationService.queryParams(2L, 76L , false , null);
        specParamps.forEach(param -> {
            System.out.println(param.getId() + " : " + param.getName());
        });
    }

    @Test
    public void testQuerySku(){
        Sku sku = goodsService.queryBySkuId(12153562122L);
        System.out.println(sku.getTitle());
        System.out.println(sku.getOwnSpec());
        System.out.println(sku.getCreateTime());
    }



    @Test
    public void testQuerySkus(){
        List<Long> skuIds = new ArrayList<Long>(){
            {
                add(2600242L);
                add(2600248L);
                add(3385376L);
                add(2868393L);
                add(2868435L);
                add(2895136L);
                add(2895158L);
            }
        };
        List<Sku> skus = goodsService.querySkuBySkuIds(skuIds);
        System.out.println(skus.size());
    }

    @Test
    public void multStock(){
        List<CartDataTransferObject> cartDTOs = new ArrayList<>();
        CartDataTransferObject obj1 = new CartDataTransferObject();
        obj1.setSkuId(27359021726L);
        obj1.setNum(3);
        cartDTOs.add(obj1);
        CartDataTransferObject obj2 = new CartDataTransferObject();
        obj2.setSkuId(26399946253L);
        obj2.setNum(2);
        cartDTOs.add(obj2);

        goodsService.decreaseStock(cartDTOs);
    }

}
