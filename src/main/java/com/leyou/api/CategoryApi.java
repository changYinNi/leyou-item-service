package com.leyou.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryApi {

    @RequestMapping("category/names")
    public List<String> queryNameByIds(@RequestParam List<Long> ids);

}
