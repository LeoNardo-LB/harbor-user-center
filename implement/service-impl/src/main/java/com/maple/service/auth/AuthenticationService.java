package com.maple.service.auth;

import com.maple.core.model.auth.AuthenticateModel;

/**
 * 认证服务服务
 */
public interface AuthenticationService {

    /**
     * 认证
     * @param authenticationModel
     * @return
     */
    AuthenticateModel authenticate(AuthenticateModel authenticationModel);

}
