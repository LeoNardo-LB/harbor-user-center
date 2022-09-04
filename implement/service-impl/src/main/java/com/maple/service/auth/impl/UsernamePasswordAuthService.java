package com.maple.service.auth.impl;

import com.google.common.collect.Maps;
import com.maple.core.auth.UserInfoService;
import com.maple.core.model.auth.AuthenticateModel;
import com.maple.core.model.auth.UsernamePasswordModel;
import com.maple.core.model.db.UserInfoModel;
import com.maple.service.auth.AuthenticationService;
import com.maple.utils.AroundLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

@Service("usernamePasswordAuthService")
public class UsernamePasswordAuthService implements AuthenticationService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final ExecutorService CERTIFICATE_QUERY_EXECUTOR = Executors.newFixedThreadPool(3);

    /**
     * 认证
     * @param authenticationModel
     * @return
     */
    @Override
    @AroundLog
    public AuthenticateModel authenticate(AuthenticateModel authenticationModel) {
        UsernamePasswordModel model = (UsernamePasswordModel) authenticationModel;
        model.authenticateCheck();

        HashMap<String, Future<UserInfoModel>> futureMap = Maps.newHashMap();
        futureMap.put("getUserByAccount", CERTIFICATE_QUERY_EXECUTOR.submit(() -> userInfoService.getUserByAccount(model.getCertificate())));
        futureMap.put("getUserByEmail", CERTIFICATE_QUERY_EXECUTOR.submit(() -> userInfoService.getUserByEmail(model.getCertificate())));
        futureMap.put("getUserByPhone", CERTIFICATE_QUERY_EXECUTOR.submit(() -> userInfoService.getUserByPhone(model.getCertificate())));

        // 获取并匹配
        AtomicBoolean match = new AtomicBoolean(false);
        futureMap.entrySet().stream()
                .filter(entry -> Objects.nonNull(getFutureValue(entry)))
                .findFirst()
                .ifPresent(entry -> {
                    UserInfoModel userInfoModel = getFutureValue(entry);
                    match.set(encoder.matches(((UsernamePasswordModel) authenticationModel).getPassword(), userInfoModel.getPassword()));
                });
        model.setAuthSuccess(match.get());
        return authenticationModel;
    }

    /**
     * 获取future的值
     * @param entry
     * @return
     */
    private UserInfoModel getFutureValue(Entry<String, Future<UserInfoModel>> entry) {
        try {
            return entry.getValue().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
