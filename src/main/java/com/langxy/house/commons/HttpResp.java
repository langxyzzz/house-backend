package com.langxy.house.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResp<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public HttpResp(int code) {
        this.code = code;
    }

    public HttpResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResp(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public HttpResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> success() {
        return new HttpResp<>(HttpRespEnum.SUCCESS.getCode());
    }

    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> successMsg() {
        return new HttpResp<>(HttpRespEnum.SUCCESS.getCode(), HttpRespEnum.SUCCESS.getMsg());
    }

    /**
     * 成功
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> successMsg(String msg) {
        return new HttpResp<>(HttpRespEnum.SUCCESS.getCode(), msg);
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> successData(T data) {
        return new HttpResp<>(HttpRespEnum.SUCCESS.getCode(), data);
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> successMsgAndData(T data) {
        return new HttpResp<>(HttpRespEnum.SUCCESS.getCode(), HttpRespEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> success(int code) {
        return new HttpResp<>(code);
    }

    /**
     * 成功
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> success(int code, String msg) {
        return new HttpResp<>(code, msg);
    }

    /**
     * 成功
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> success(int code, T data) {
        return new HttpResp<>(code, data);
    }

    /**
     * 成功
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> success(int code, String msg, T data) {
        return new HttpResp<>(code, msg, data);
    }

    /**
     * 失败
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> error(int code) {
        return new HttpResp<>(code);
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> error(int code, String msg) {
        return new HttpResp<>(code, msg);
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResp<T> error(int code, String msg, T data) {
        return new HttpResp<>(code, msg, data);
    }
}

