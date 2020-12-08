package com.yanyun;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/07/20:06
 * @description
 */
public class RocketMQSendTest {
    @Test
    public void testSend() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //1. 创建消息生产者, 指定生产者所属的组名
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group");
        //2. 指定Nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3. 启动生产者
        producer.start();
        //4. 创建消息对象，指定主题、标签和消息体
        Message msg = new Message("myTopic", "myTag", "RocketMQ Message".getBytes(StandardCharsets.UTF_8));
        //5. 发送消息
        SendResult sendResult = producer.send(msg, 10000);
        System.out.println(sendResult);
        //6. 关闭生产者
        producer.shutdown();
    }

    @Test
    public void testReceive() throws MQClientException {
        //1. 创建消息消费者, 指定消费者所属的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer-group");
        //2. 指定Nameserver地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //3. 指定消费者订阅的主题和标签
        consumer.subscribe("myTopic", "*");
        //4. 设置回调函数，编写处理消息的方法
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("Receive New Messages: " + msgs);
                //返回消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5. 启动消息消费者
        consumer.start();
        System.out.println("Consumer Started.");
    }
}
