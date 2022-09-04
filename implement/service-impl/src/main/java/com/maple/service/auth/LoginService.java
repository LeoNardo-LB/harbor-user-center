package com.maple.service.auth;

import com.maple.core.model.auth.AuthenticateModel;

public interface LoginService {

    String login(AuthenticateModel model);

    Boolean logout(AuthenticateModel model);

    Boolean refreshToken(AuthenticateModel model);

    Boolean invalidateToken(AuthenticateModel model);

    Boolean register(AuthenticateModel model);

}
