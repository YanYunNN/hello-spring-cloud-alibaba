package com.yanyun.service;

import com.yanyun.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:17
 * @description
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    //扣减库存
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
