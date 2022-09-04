package com.maple.portal.controller.advisor;

import com.maple.core.exception.BizException;
import com.maple.core.result.MvcResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BizAdvisor {

    @ExceptionHandler(value = BizException.class)
    public MvcResult<String> commonBizExHandler(BizException e) {
        return MvcResult.fail(e.getCode(), e.getMessage());
    }

}
