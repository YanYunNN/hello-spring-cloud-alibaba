package com.yanyun.mq;

import com.yanyun.dao.TxLogDao;
import com.yanyun.domain.Order;
import com.yanyun.domain.TxLog;
import com.yanyun.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:44
 * @description
 */
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
public class OrderServiceListener implements RocketMQLocalTransactionListener {
    @Autowired
    private TxLogDao txLogDao;
    @Autowired
    private OrderService orderService;

    /**
     * 执行本地事务
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            //本地事务
            orderService.createOrder((String) msg.getHeaders().get("txId"), (Order) arg);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 执行消息回查
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        //查询日志记录
        TxLog txLog = txLogDao.findById((String) msg.getHeaders().getOrDefault("txId", null)).orElse(null);
        if (txLog == null) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
