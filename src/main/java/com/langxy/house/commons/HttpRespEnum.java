package com.langxy.house.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpRespEnum {
    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 系统异常
     */
    SYS_EXC(-1, "system exception"),
    /**
     * 参数错误
     */
    PARAM_EXC(100002, "params error");

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

}

