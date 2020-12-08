package com.yanyun.service;

import com.yanyun.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/07/20:50
 * @description
 */
@Service
@Slf4j
public class OrderService {
    public void save(Order order) {
        log.info("db store!");
    }
}
