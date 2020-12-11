package com.yanyun.controller;

import com.yanyun.service.impl.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/11/29/15:24
 * @description
 */
@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 减少库存
     * @param pid
     * @param num
     */
    @PostMapping("/product/reduceInventory")
    public void reduceInventory(Integer pid, int num) {
        productService.reduceInventory(pid, num);
    }
}
