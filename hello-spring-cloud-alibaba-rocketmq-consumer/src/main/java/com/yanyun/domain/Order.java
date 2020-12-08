package com.yanyun.domain;

import lombok.Data;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/07/20:36
 * @description
 */
@Data
public class Order {
    private String pname;
    private String username;
    private int uid;
    private int pid;
    private int pprice;
    private int number;

}
