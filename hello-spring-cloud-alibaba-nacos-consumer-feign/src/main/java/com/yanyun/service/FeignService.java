package com.yanyun.service;

import com.yanyun.service.impl.FallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/11/29/15:57
 * @description @FeignClient("服务名") 注解来指定调用哪个服务
 */

@FeignClient(value = "nacos-provider", fallback = FallbackServiceImpl.class)
public interface FeignService {
    @GetMapping("/test/{message}")
    String test(@PathVariable("message") String message);

}
