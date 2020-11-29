package com.yanyun.service.impl;

import com.yanyun.service.FallbackService;
import com.yanyun.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/11/29/16:11
 * @description
 */
@Component
public class FallbackServiceImpl implements FallbackService, FeignService {
    @Override
    public String test(String message) {
        return "test fallback";
    }
}
