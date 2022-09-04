package com.maple.core.result;

import lombok.Data;

@Data
public class AuthenticateResult {

    /**
     * 认证是否成功, 成功的话字段返回true
     */
    private Boolean authSuccess;

    /**
     * 用户凭证
     */
    private String certificate;

    /**
     * 其他信息
     */
    private String loginWay;

}
