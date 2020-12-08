package com.yanyun.dao;

import com.yanyun.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:39
 * @description
 */
public interface TxLogDao extends JpaRepository<TxLog, String> {
}
