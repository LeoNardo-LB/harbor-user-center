package com.maple.core.model.auth;

import lombok.Data;

@Data
public abstract class AuthenticateModel {

    private String certificate;

    private Boolean authSuccess;


    public void setAuthSuccess(Boolean authSuccess) {
        this.authSuccess = authSuccess;
        cleanAuthKey();
    }

    /**
     * 认证信息检查
     */
    abstract public void authenticateCheck();

    /**
     * 清除认证关键信息
     */
    abstract protected void cleanAuthKey();

}
