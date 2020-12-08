package com.yanyun.service;

import com.yanyun.dao.OrderDao;
import com.yanyun.dao.TxLogDao;
import com.yanyun.domain.Order;
import com.yanyun.domain.TxLog;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:37
 * @description
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TxLogDao txLogDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void createOrderBefore(Order order) {
        String txId = UUID.randomUUID().toString();
        //发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(),
                order
        );
    }

    /**
     * 本地事务
     * @param txId
     * @param order
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(String txId, Order order) {
        //本地事物代码
        orderDao.save(order);
        //记录日志到数据库,回查使用
        TxLog txLog = new TxLog();
        txLog.setTxLogId(txId);
        txLog.setContent("事物测试");
        txLog.setDate(new Date());
        txLogDao.save(txLog);
    }
}