package com.maple.core.result;

import com.maple.core.result.base.BaseResult;
import lombok.Data;

@Data
public class MvcResult<T> extends BaseResult<T> {

    private int code;

    public static <T> MvcResult<T> success(T data) {
        MvcResult<T> result = new MvcResult<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setData(data);
        return result;
    }

    public static <T> MvcResult<T> fail(String message) {
        MvcResult<T> result = new MvcResult<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public static <T> MvcResult<T> fail(int code, String message) {
        MvcResult<T> result = new MvcResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
