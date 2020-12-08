package com.yanyun.service;

import com.alibaba.fastjson.JSON;
import com.yanyun.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/07/20:46
 * @description 发送短信的服务
 */
@Slf4j
@Service
@RocketMQMessageListener(
        consumerGroup = "shop",//消费者分组
        topic = "order-topic",//要消费的主题
        consumeMode = ConsumeMode.CONCURRENTLY, //消费模式:无序和有序
        messageModel = MessageModel.CLUSTERING  //消息模式:广播和集群,默认是集群
        //广播消费: 每个消费者实例都会收到消息,也就是一条消息可以被每个消费者实例处理；
        //集群消费: 一条消息只能被一个消费者实例消费
)
public class SmsService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
    }
}
