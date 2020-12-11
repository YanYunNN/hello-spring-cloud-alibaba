package com.yanyun.domain;

import lombok.Data;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/07/20:36
 * @description
 */
@Data
public class Product {
    private int pid;
    private String pname;
    private int pprice;
    private int number;
    private int stock;
}
