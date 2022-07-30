package com.leyou.mapper;

import com.leyou.pojo.SpecParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecParamMapper {

    public List<SpecParam> querySpecParams(@Param("gid") Long gid, @Param("cid") Long cid,
                                          @Param("generic")Boolean generic, @Param("searching") Boolean searching);

}
