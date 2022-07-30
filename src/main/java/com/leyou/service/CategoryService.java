package com.leyou.service;

import com.leyou.mapper.CategoryMapper;
import com.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> searchCategories(Long pid){
        //Category category = new Category();
        //category.setParent_id(pid);
        List<Category> categories = categoryMapper.searchCategories(pid);//.select(category);
        return categories;
    }

    public List<String> getCategoryNames(List<Long> ids){
        return categoryMapper.getCategoryNames(ids);
    }

    public List<Category> searchCategoriesByBrandId(Long bid){
        List<Category> categories = categoryMapper.searchCategoriesByBrandId(bid);
        return categories;
    }

}
