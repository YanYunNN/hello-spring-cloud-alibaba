package com.yanyun.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/08/10:35
 * @description 事物日志
 */

@Entity(name = "shop_txlog")
@Data
public class TxLog {
    @Id
    private String txLogId;
    private String content;
    private Date date;
    private String id;
}
