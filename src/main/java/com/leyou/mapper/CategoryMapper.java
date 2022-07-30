package com.leyou.mapper;

import com.leyou.pojo.Category;
//import org.apache.ibatis.annotations.Mapper;或者在引导类加@MapperScan
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;

import java.util.List;

//@org.apache.ibatis.annotations.Mapper
//extends Mapper<Category>, IdListMapper<Category, Long>
@Mapper
public interface CategoryMapper {

    public List<Category> searchCategories(Long pid);

    public List<String> getCategoryNames(List<Long> ids);

    public List<Category> searchCategoriesByBrandId(Long bid);

}
