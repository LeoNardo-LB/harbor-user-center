package com.maple.core.enums;

import org.apache.commons.lang3.StringUtils;

public enum Gender {

    NO_DEFINE(0, "未定义"),
    MAN(1, "男"),
    WOMAN(2, "女"),
    ;

    /**
     * 代码
     */
    private int code;

    /**
     * 描述
     */
    private String desc;

    private Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取性别code
     * @return
     */
    public static int getCode(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        for (Gender gender : values()) {
            if (gender.desc.equals(str)) {
                return gender.code;
            }
        }
        return 0;
    }

    /**
     * 获取性别code
     * @return
     */
    public static String getDesc(int code) {
        for (Gender gender : values()) {
            if (gender.code == code) {
                return gender.desc;
            }
        }
        return null;
    }
}
