package com.maple.ucenter.model;

import lombok.Data;

@Data
public class JwtModel {

    /**
     * 主体, 一般是uid
     */
    private String subject;

}
