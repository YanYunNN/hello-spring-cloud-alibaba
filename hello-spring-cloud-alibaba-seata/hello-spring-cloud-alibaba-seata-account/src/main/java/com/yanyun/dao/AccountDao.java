package com.yanyun.dao;

import com.yanyun.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:39
 * @description
 */
public interface AccountDao extends JpaRepository<Account, Integer> {
    Account findByUserId(long userId);
}
