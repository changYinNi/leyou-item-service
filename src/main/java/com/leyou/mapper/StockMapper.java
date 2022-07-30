package com.leyou.mapper;

import com.leyou.pojo.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper {

    public void addStock(@Param("stock") Stock stock);

    public void deleteStock(@Param("skuId")Long skuId);

    public int decreaseStock(@Param("skuId")Long skuId, @Param("num")Integer num);

}
