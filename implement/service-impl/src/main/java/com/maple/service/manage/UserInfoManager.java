package com.maple.service.manage;

import com.maple.core.model.db.UserInfoModel;
import com.maple.service.share.result.ImplResult;

public interface UserInfoManager {

    /**
     * 添加用户
     * @param userInfoModel
     * @return
     */
    ImplResult<Boolean> addUser(UserInfoModel userInfoModel);

}
