package com.maple.service.manage.impl;

import com.alibaba.fastjson2.JSON;
import com.maple.core.auth.UserInfoService;
import com.maple.core.exception.BizException;
import com.maple.core.model.db.UserInfoModel;
import com.maple.service.manage.UserInfoManager;
import com.maple.service.share.result.ImplResult;
import com.maple.utils.AroundLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoManagerImpl implements UserInfoManager {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 添加用户
     * @param userInfoModel
     * @return
     */
    @Override
    @AroundLog
    public ImplResult<Boolean> addUser(UserInfoModel userInfoModel) {
        try {
            return ImplResult.success(userInfoService.addUser(userInfoModel));
        } catch (DuplicateKeyException e) {
            log.warn("用户名/邮箱/手机号已存在, JSON: " + JSON.toJSONString(userInfoModel));
            return ImplResult.success(false);
        } catch (BizException e){
            return ImplResult.fail(500, e.getMessage());
        }
    }

}
