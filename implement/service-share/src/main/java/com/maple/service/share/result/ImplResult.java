package com.maple.service.share.result;

import com.maple.core.result.base.BaseResult;

public class ImplResult<T> extends BaseResult<T> {

    public static <T> ImplResult<T> success(T data) {
        ImplResult<T> result = new ImplResult<>();
        result.code = 200;
        result.data = data;
        result.success = true;
        return result;
    }

    public static <T> ImplResult<T> fail(int code, String message) {
        ImplResult<T> result = new ImplResult<>();
        result.code = code;
        result.success = false;
        result.message = message;
        return result;
    }

}
