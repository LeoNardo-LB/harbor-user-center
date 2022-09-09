package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.UserInfoModel;
import com.maple.core.request.UserPageQuery;
import com.maple.core.result.base.BaseResult;

import java.util.List;

public interface UserInfoService {

    BaseResult<UserInfoModel> getUserById(Long id);

    BaseResult<UserInfoModel> getUserByUId(Long uid);

    BaseResult<UserInfoModel> getUserByAccount(String account);

    BaseResult<UserInfoModel> getUserByPhone(String phoneNumber);

    BaseResult<UserInfoModel> getUserByEmail(String email);

    BaseResult<Page<UserInfoModel>> pageListUsers(UserPageQuery userPageQuery);

    BaseResult<Boolean> addUser(UserInfoModel userInfoModel);

    BaseResult<Boolean> modifyUserById(UserInfoModel userInfoModel);

    BaseResult<Long> modifyUsersByIds(List<UserInfoModel> userInfoModels);

    BaseResult<Boolean> disableUserById(Long id);

    BaseResult<Boolean> disableUserByUid(Long uid);

    BaseResult<Boolean> removeUserById(Long id);

    BaseResult<Boolean> removeUserByUid(Long uid);

}
