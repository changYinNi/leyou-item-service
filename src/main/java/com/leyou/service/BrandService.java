package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.mapper.BrandMapper;
import com.leyou.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandsByPage(String key, Integer page,
                                               Integer row, String sortBy, Boolean desc) {
        //添加排序条件
        String[]orderArray = {"id", "letter"};
        String orderByString = "";
        if(StringUtils.isNotEmpty(sortBy) && Arrays.asList(orderArray).contains(sortBy.toLowerCase())){
            if(desc){
                orderByString = orderByString + sortBy + " desc";
            }else{
                orderByString = orderByString + sortBy + " asc";
            }
        }else{
            orderByString = orderByString + "letter desc";
        }
        //添加分页条件
        PageHelper.startPage(page, row, orderByString);

        List<Brand> brands = brandMapper.searchBrands(key, key);
        //根据name模糊查询，或者根据首字母模糊查询
        if(StringUtils.isNotBlank(key)){
            //brands =
        }
        PageInfo<Brand> pageInfoBrands = new PageInfo<>(brands);
        return new PageResult<>(pageInfoBrands.getTotal(), pageInfoBrands.getList());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void addBrandRelated(Brand brand, List<Long> cids){
        Long addResult = brandMapper.insertBrand(brand);
        Long bid = brand.getId();
        System.out.println("new bid : " + addResult + "   " + bid);

        for (Long cidItem: cids) {
            System.out.println("cidItem : " + cidItem);
            brandMapper.insertBrandCategory(cidItem, bid);
        }
    }

    public List<Brand> queryBrandsByCid(Long cid){
        return brandMapper.queryBrandsByCid(cid);
    }

    public Brand getBrand(Long id){
        return brandMapper.getBrand(id);
    }

}
