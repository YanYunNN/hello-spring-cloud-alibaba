package com.yanyun.service.impl;

import com.yanyun.dao.ProductDao;
import com.yanyun.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductService {
    @Autowired
    ProductDao productDao;

    /**
     * 减库存
     * @param pid
     * @param num
     */
    public void reduceInventory(Integer pid, int num) {
        Product product = productDao.findById(pid).get();
        product.setStock(product.getStock() - num);
        //减库存
        productDao.save(product);
    }

    public void reduceInventoryError(Integer pid, Integer number) {
        Product product = productDao.findById(pid).get();
        if (product.getStock() < number) {
            throw new RuntimeException("库存不足");
        }
        int i = 1 / 0;
        product.setStock(product.getStock() - number);
        productDao.save(product);
    }
}
