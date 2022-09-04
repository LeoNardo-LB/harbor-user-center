package com.maple.core.model.auth;

import lombok.Data;

@Data
public abstract class AuthenticateModel {

    private String certificate;


    abstract public void authenticateCheck();

}
