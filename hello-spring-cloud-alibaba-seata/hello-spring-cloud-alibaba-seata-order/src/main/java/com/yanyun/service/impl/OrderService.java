package com.yanyun.service.impl;

import com.alibaba.fastjson.JSON;
import com.yanyun.dao.OrderDao;
import com.yanyun.domain.Order;
import com.yanyun.domain.Product;
import com.yanyun.service.AccountService;
import com.yanyun.service.ProductService;
import com.yanyun.service.IOrderService;
import com.yanyun.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/20:36
 * @description
 */
@Slf4j
@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    StorageService storageService;
    @Resource
    AccountService accountService;

    /**
     * 全局事务控制
     * @param pid
     * @return
     */
    @GlobalTransactional
    public Order createOrder(Integer pid) {
        //1 调用商品微服务,查询商品信息
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));
        //2 下单(创建订单)
        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setProductId(133879L);
        order.setCount(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        //3 扣库存
        productService.reduceInventory(pid, order.getCount());
        //4 向mq中投递一个下单成功的消息
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }

    @Override
    public void create(Order order) {
        log.info("------>开始新建订单");
        orderDao.save(order);
        log.info("------>订单微服务开始调用库存，扣减count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------>订单微服务开始调用库存，扣减end");
        log.info("------>订单微服务开始调用账户，扣减money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------>订单微服务开始调用账户，扣减end");
        log.info("------>开始修改订单状态");
        order.setStatus(1);
        orderDao.save(order);
        log.info("------>修改订单状态完毕");
        log.info("------>下单完毕");
    }
}
