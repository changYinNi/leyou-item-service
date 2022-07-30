package com.leyou.mapper;

import com.leyou.pojo.SpuDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpuDetailMapper {

    public void addSpuDetail(@Param("spuDetail") SpuDetail spuDetail);

    public SpuDetail querySpuDetailBySpuId(@Param("spuId") Long spuId);

    public void modifySpuDetail(@Param("spuDetail") SpuDetail spuDetail);

}
