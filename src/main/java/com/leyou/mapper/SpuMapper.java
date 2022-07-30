package com.leyou.mapper;

import com.leyou.bo.SpuBo;
import com.leyou.pojo.Spu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuMapper {

    public List<SpuBo> searchSpus(String title, Boolean saleable);

    public void addSpu(@Param("spu") Spu spu);

    public void modifySpu(@Param("spu") Spu spu);

    public Spu querySpuById(@Param("id")Long id);

}
