package com.maple.service.auth;

import com.maple.core.model.auth.AuthenticateModel;
import com.maple.core.result.AuthenticateResult;

/**
 * 认证服务服务
 */
public interface AuthenticationService {

    /**
     * 认证
     * @param authenticationModel
     * @return
     */
    AuthenticateResult authenticate(AuthenticateModel authenticationModel);

}
