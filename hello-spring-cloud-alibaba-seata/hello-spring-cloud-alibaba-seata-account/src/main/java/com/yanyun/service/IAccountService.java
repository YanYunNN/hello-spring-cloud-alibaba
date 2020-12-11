package com.yanyun.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/20:37
 * @description
 */
@Service
public interface IAccountService {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money  金额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
