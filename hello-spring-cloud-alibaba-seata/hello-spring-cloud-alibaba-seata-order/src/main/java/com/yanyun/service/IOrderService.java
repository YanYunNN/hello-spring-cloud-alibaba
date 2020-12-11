package com.yanyun.service;

import com.yanyun.domain.Order;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:08
 * @description
 */
public interface IOrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
