package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.UserInfoModel;
import com.maple.core.request.UserPageQuery;

import java.util.List;

public interface UserInfoService {

    UserInfoModel getUserById(Long id);

    UserInfoModel getUserByUId(Long uid);

    UserInfoModel getUserByAccount(String account);

    UserInfoModel getUserByPhone(String phoneNumber);

    UserInfoModel getUserByEmail(String email);

    Page<UserInfoModel> pageListUsers(UserPageQuery userPageQuery);

    Boolean addUser(UserInfoModel userInfoModel);

    Boolean modifyUserById(UserInfoModel userInfoModel);

    Long modifyUsersByIds(List<UserInfoModel> userInfoModels);

    Boolean disableUserById(Long id);

    Boolean disableUserByUid(Long uid);

    Boolean removeUserById(Long id);

    Boolean removeUserByUid(Long uid);

}
