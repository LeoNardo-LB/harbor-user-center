package com.maple.core.result.base;

import lombok.Data;

@Data
public class BaseResult<T> {

    /**
     * 是否成功
     */
    protected boolean success;

    /**
     * 状态码
     */
    protected int code;

    /**
     * 数据
     */
    protected T data;

    /**
     * 信息
     */
    protected String message;

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> success(int code, T data, String message) {
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(true);
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> success(T data, String message) {
        return success(1001, data, message);
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> success(T data) {
        return success(1001, data, null);
    }

    /**
     * 失败
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    protected static <T> BaseResult<T> fail(int code, String message) {
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败
     * @param message
     * @param <T>
     * @return
     */
    protected static <T> BaseResult<T> fail(String message) {
        return fail(1002, message);
    }

}
