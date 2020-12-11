package com.yanyun.controller;

import com.yanyun.domain.CommonResult;
import com.yanyun.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:36
 * @description
 */
@RestController
public class StorageController {
    @Autowired
    private IStorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成功！");
    }
}
