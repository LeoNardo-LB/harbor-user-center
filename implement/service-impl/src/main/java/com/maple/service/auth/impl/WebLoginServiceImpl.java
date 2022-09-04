package com.maple.service.auth.impl;

import com.alibaba.fastjson2.JSON;
import com.maple.core.cache.CacheService;
import com.maple.core.model.auth.AuthenticateModel;
import com.maple.core.model.auth.UsernamePasswordModel;
import com.maple.core.result.AuthenticateResult;
import com.maple.service.auth.AuthenticationService;
import com.maple.service.auth.LoginService;
import com.maple.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WebLoginServiceImpl implements LoginService {

    @Autowired
    @Qualifier("usernamePasswordAuthService")
    private AuthenticationService authenticationService;

    @Autowired
    private CacheService cacheService;

    /**
     * web登录的业务场景前缀
     */
    private final static String WEB_LOGIN_SCENE = "WebLogin";

    /**
     * todo 用jwt来做
     * @param model
     * @return
     */
    @Override
    public String login(AuthenticateModel model) {
        AuthenticateResult result = authenticationService.authenticate(model);
        cacheService.putObject(CacheUtils.genKey(WEB_LOGIN_SCENE, result.getLoginWay(), model.getCertificate()), result);
        return JSON.toJSONString(result);
    }

    @Override
    public Boolean logout(AuthenticateModel model) {
        UsernamePasswordModel passwordModel = (UsernamePasswordModel) model;
        return cacheService.deleteKey(CacheUtils.genKey(WEB_LOGIN_SCENE, passwordModel.getAuthWay(), passwordModel.getCertificate()));
    }

    @Override
    public Boolean refreshToken(AuthenticateModel model) {
        return null;
    }

    @Override
    public Boolean invalidateToken(AuthenticateModel model) {
        return null;
    }

    @Override
    public Boolean register(AuthenticateModel model) {
        return null;
    }

}
