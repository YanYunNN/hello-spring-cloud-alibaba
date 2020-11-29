package com.yanyun.controller;

import com.yanyun.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/11/29/15:59
 * @description
 */
@RestController
public class NacosConsumerFeignController {
    @Autowired
    FeignService feignService;

    @GetMapping(value = "/test/hi")
    public String test() {
        return feignService.test("Hi Feign");
    }

}
