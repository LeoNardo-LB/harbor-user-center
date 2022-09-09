package com.maple.service.auth.impl;

import com.alibaba.fastjson2.JSON;
import com.maple.core.auth.UserInfoService;
import com.maple.core.cache.CacheService;
import com.maple.core.enums.LoginAuthWay;
import com.maple.core.model.auth.AuthenticateModel;
import com.maple.core.model.auth.UsernamePasswordModel;
import com.maple.core.model.db.UserInfoModel;
import com.maple.service.auth.AuthenticationService;
import com.maple.service.auth.LoginService;
import com.maple.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("webLoginService")
public class WebLoginService implements LoginService {

    @Autowired
    @Qualifier("usernamePasswordAuthService")
    private AuthenticationService authenticationService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private BCryptPasswordEncoder encoder;

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
        UsernamePasswordModel passwordModel = (UsernamePasswordModel) model;
        String key = CacheUtils.genKey(WEB_LOGIN_SCENE, passwordModel.getAuthWay(), passwordModel.getCertificate());
        UsernamePasswordModel _passwordModel = cacheService.getObject(key, UsernamePasswordModel.class);
        if(Objects.isNull(_passwordModel)){
            passwordModel = (UsernamePasswordModel) authenticationService.authenticate(passwordModel);
            cacheService.putObject(key, passwordModel);
            _passwordModel = passwordModel;
        }
        return JSON.toJSONString(_passwordModel);
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
        model.authenticateCheck();
        UsernamePasswordModel passwordModel = (UsernamePasswordModel) model;
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setPassword(encoder.encode(passwordModel.getPassword()));
        // 根据凭证信息去注册用户
        if (LoginAuthWay.ACCOUNT.getCode().equals(passwordModel.getAuthWay())) {
            userInfoModel.setAccount(passwordModel.getCertificate());
        } else if (LoginAuthWay.EMAIL.getCode().equals(passwordModel.getAuthWay())) {
            userInfoModel.setEmail(passwordModel.getCertificate());
        } else if (LoginAuthWay.PHONE_NUMBER.getCode().equals(passwordModel.getAuthWay())) {
            userInfoModel.setPhoneNumber(passwordModel.getCertificate());
        }
        return userInfoService.addUser(userInfoModel).getData();
    }

}
