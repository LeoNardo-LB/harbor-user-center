package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.UserModel;
import com.maple.core.request.UserPageQuery;

import java.util.List;

public interface UserInfoService {

    UserModel getUserById(Long id);

    UserModel getUserByUId(Long uid);

    UserModel getUserByAccount(String account);

    UserModel getUserByPhone(String phoneNumber);

    UserModel getUserByEmail(String email);

    Page<UserModel> pageListUsers(UserPageQuery userPageQuery);

    Boolean addUser(UserModel userModel);

    Boolean modifyUserById(UserModel userModel);

    Long modifyUsersByIds(List<UserModel> userModels);

    Boolean disableUserById(Long id);

    Boolean disableUserByUid(Long uid);

    Boolean removeUserById(Long id);

    Boolean removeUserByUid(Long uid);

}
