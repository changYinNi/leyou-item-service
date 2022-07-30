package com.leyou.mapper;

import com.leyou.pojo.SpecGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpecGroupMapper {

    public List<SpecGroup> queryGroupsBycid(Long cid);

}
