package com.leyou.mapper;

import com.leyou.pojo.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkuMapper {

    public void addSku(@Param("sku")Sku sku);

    public List<Sku> querySkusBySpuId(@Param("spuId")Long spuId);

    public void deleteSkus(@Param("spuId")Long spuId);

    public Sku querySkuById(@Param("skuId")Long skuId);

    public List<Sku> querySkuBySkuIds(List<Long> skuIds);

}
