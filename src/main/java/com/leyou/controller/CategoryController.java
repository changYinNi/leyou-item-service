package com.leyou.controller;

import com.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesBypid(
            @RequestParam(value = "pid", defaultValue = "0") Long pid){
        try {
            if(pid == null || pid < 0){
                //400: 参数不合法
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            List<Category> categories = categoryService.searchCategories(pid);
            if(CollectionUtils.isEmpty(categories)){
                //404: 资源服务器未找到
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            // 200:查询成功
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500:服务器内部错误
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryBrandId(@PathVariable("bid")Long bid){
        List<Category> categories = categoryService.searchCategoriesByBrandId(bid);
        return ResponseEntity.ok(categories);
    }

    @RequestMapping("names")
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids")List<Long> ids){
        List<String> categories = categoryService.getCategoryNames(ids);
        if(CollectionUtils.isEmpty(categories)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

}
