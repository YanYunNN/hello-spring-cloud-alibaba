package com.yanyun.service;

import com.yanyun.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:17
 * @description
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
    //扣减账户余额
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
