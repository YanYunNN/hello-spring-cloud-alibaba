package com.yanyun.api;

/**
 * @author xcai
 * @version 1.0
 * @date 2020/12/04/10:28
 * @description 封装API的错误码
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
