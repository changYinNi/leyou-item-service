package com.leyou.mapper;

import com.leyou.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrandMapper {

    public List<Brand> searchBrands(String name, String letter);

    public Brand getBrand(Long id);

    //这里使用@Param修饰参数，在获取自动增长的keyProperty , id要加上 brand.id
    public Long insertBrand(@Param("brand") Brand brand);

    public void insertBrandCategory(@Param("cid") Long cid, @Param("bid") Long bid);

    public List<Brand> queryBrandsByCid(Long cid);

}
