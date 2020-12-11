package com.yanyun.dao;

import com.yanyun.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:39
 * @description
 */
public interface StorageDao extends JpaRepository<Storage, Integer> {
    Storage findByProductId(Long pid);
}
