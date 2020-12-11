package com.yanyun.service;

import com.yanyun.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/20:37
 * @description
 */
@FeignClient(value = "service-product")
@Service
public interface ProductService {
    /**
     * 减库存
     * @param pid
     * @param num
     */
    @PostMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid, @RequestParam("num") int num);

    /**
     * 减库存
     * @param pid
     * @return
     */
    @GetMapping("/product/{pid}}")
    Product findByPid(Integer pid);
}
