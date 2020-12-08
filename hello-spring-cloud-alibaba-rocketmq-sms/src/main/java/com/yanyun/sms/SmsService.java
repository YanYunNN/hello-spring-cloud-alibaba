package com.yanyun.sms;

import com.alibaba.fastjson.JSON;
import com.yanyun.domain.Order;
import com.yanyun.domain.User;
import com.yanyun.service.UserService;
import com.yanyun.sms.SmsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/11:40
 * @description
 */
@Slf4j
@Service("shopSmsService")
@RocketMQMessageListener(
        consumerGroup = "shop",                 /* 消费者组名 */
        topic = "order-topic",                  /* 消费主题 */
        consumeMode = ConsumeMode.CONCURRENTLY, /* 消费模式 */
        messageModel = MessageModel.CLUSTERING  /* 消息模式 */
)
public class SmsService implements RocketMQListener<Order> {
    @Autowired
    UserService userService;

    @Override
    public void onMessage(Order message) {
        log.info("接收到了一个订单信息{},接下来就可以发送短信通知了", message);
        /* 根据uid 获取手机号 */
        User user = userService.findById(message.getUid());
        /* 生成验证码 */
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(new Random().nextInt(9) + 1);
        }
        String smsCode = builder.toString();
        Param param = new Param(smsCode);
        try {
            /* 发送短信 {"code":"123456"} */
            SmsUtils.sendSms(user.getTelephone(), "旅游网", "SMS_170836451", JSON.toJSONString(param));
            log.info("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Param {
        private String code;
    }
}
