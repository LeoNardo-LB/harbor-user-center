package com.maple.core.enums;

import lombok.Getter;

@Getter
public enum LoginAuthWay {

    ACCOUNT("account", "账号"),
    PHONE_NUMBER("phone_number", "手机号"),
    EMAIL("email", "电子邮件");

    private String code;
    private String desc;

    LoginAuthWay(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
