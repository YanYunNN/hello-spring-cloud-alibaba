package com.yanyun.service;

import com.yanyun.dao.AccountDao;
import com.yanyun.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:49
 * @description
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        Account account = accountDao.findByUserId(userId);
        account.setResidue(account.getResidue().subtract(money));
        account.setUsed(account.getUsed().add(money));
        accountDao.save(account);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }
}
