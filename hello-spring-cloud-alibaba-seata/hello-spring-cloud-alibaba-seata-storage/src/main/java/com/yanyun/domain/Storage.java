package com.yanyun.domain;

import lombok.Data;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/11/17:31
 * @description
 */
@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
