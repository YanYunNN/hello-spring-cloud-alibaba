package com.yanyun.dao;

import com.yanyun.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:39
 * @description
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
}
