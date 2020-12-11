package com.yanyun.service;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:33
 * @description
 */
public interface IStorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
