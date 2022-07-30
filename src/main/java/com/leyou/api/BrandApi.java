package com.leyou.api;

import com.leyou.pojo.Brand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface BrandApi {

    @RequestMapping("brand/{id}")
    public Brand queryBrandById(@PathVariable("id") Long id);

}
