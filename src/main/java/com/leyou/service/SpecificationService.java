package com.leyou.service;

import com.leyou.mapper.SpecGroupMapper;
import com.leyou.mapper.SpecParamMapper;
import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> queryGroupsBycid(Long cid){
        return specGroupMapper.queryGroupsBycid(cid);
    }

    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching){
        return specParamMapper.querySpecParams(gid, cid, generic, searching);
    }

    public List<SpecGroup> queryGroupsWithParams(Long cid){
        List<SpecGroup> groups = queryGroupsBycid(cid);
        groups.forEach(group -> {
            List<SpecParam> params = queryParams(group.getId(), null, null , null);
            System.out.println("params.size() : " + params.size());
            group.setParams(params);
        });
        System.out.println("groups.size() : " + groups.size());
        return groups;
    }

}
