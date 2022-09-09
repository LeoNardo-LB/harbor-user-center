package com.maple.core.auth.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.convert.UserInfoConvertor;
import com.maple.core.enums.Status;
import com.maple.core.exception.BizException;
import com.maple.core.model.db.UserInfoModel;
import com.maple.core.request.UserPageQuery;
import com.maple.core.auth.UserInfoService;
import com.maple.core.result.base.BaseResult;
import com.maple.dal.dao.MuUserInfoDao;
import com.maple.dal.entity.MuUserInfo;
import com.maple.utils.AroundLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private MuUserInfoDao userInfoDao;

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<UserInfoModel> getUserById(Long id) {
        MuUserInfo userInfo = userInfoDao.selectById(id);
        return BaseResult.success(UserInfoConvertor.convert2Model(userInfo));
    }

    /**
     * @param uid
     * @return
     */
    @Override
    public BaseResult<UserInfoModel> getUserByUId(Long uid) {
        LambdaQueryWrapper<MuUserInfo> wrapper = new LambdaQueryWrapper<MuUserInfo>().eq(MuUserInfo::getUserId, uid);
        return BaseResult.success(UserInfoConvertor.convert2Model(userInfoDao.selectOne(wrapper)));
    }

    /**
     * @param account
     * @return
     */
    @Override
    public BaseResult<UserInfoModel> getUserByAccount(String account) {
        LambdaQueryWrapper<MuUserInfo> wrapper = new LambdaQueryWrapper<MuUserInfo>().eq(MuUserInfo::getAccount, account);
        return BaseResult.success(UserInfoConvertor.convert2Model(userInfoDao.selectOne(wrapper)));
    }

    /**
     * @param phoneNumber
     * @return
     */
    @Override
    public BaseResult<UserInfoModel> getUserByPhone(String phoneNumber) {
        LambdaQueryWrapper<MuUserInfo> wrapper = new LambdaQueryWrapper<MuUserInfo>().eq(MuUserInfo::getPhoneNumber, phoneNumber);
        return BaseResult.success(UserInfoConvertor.convert2Model(userInfoDao.selectOne(wrapper)));
    }

    /**
     * @param email
     * @return
     */
    @Override
    public BaseResult<UserInfoModel> getUserByEmail(String email) {
        LambdaQueryWrapper<MuUserInfo> wrapper = new LambdaQueryWrapper<MuUserInfo>().eq(MuUserInfo::getEmail, email);
        return BaseResult.success(UserInfoConvertor.convert2Model(userInfoDao.selectOne(wrapper)));
    }

    /**
     * @param userPageQuery
     * @return
     */
    @Override
    public BaseResult<Page<UserInfoModel>> pageListUsers(UserPageQuery userPageQuery) {
        LambdaQueryWrapper<MuUserInfo> wrapper = new LambdaQueryWrapper<MuUserInfo>();
        Page<MuUserInfo> page = userInfoDao.selectPage(Page.of(userPageQuery.getPageNum(), userPageQuery.getPageSize()), wrapper);
        return BaseResult.success(UserInfoConvertor.convert2ModelPage(page));
    }

    /**
     * @param userInfoModel
     * @return
     */
    @Override
    @AroundLog
    public BaseResult<Boolean> addUser(UserInfoModel userInfoModel) {
        try {
            userInfoModel.checkAddInfo();
            MuUserInfo entity = UserInfoConvertor.convert2Do(userInfoModel);
            Assert.isTrue(userInfoDao.insert(entity) > 0, () -> new BizException("新增用户失败", 1002));
            userInfoModel.setId(entity.getId());
            return BaseResult.success(true);
        } catch (DuplicateKeyException e) {
            log.warn("已有重复用户, " + e.getMessage());
            return BaseResult.success(false, "已有重复用户, " + e.getMessage());
        }
    }

    /**
     * @param userInfoModel
     * @return
     */
    @Override
    public BaseResult<Boolean> modifyUserById(UserInfoModel userInfoModel) {
        userInfoModel.checkModifyInfo();
        return BaseResult.success(userInfoDao.updateById(UserInfoConvertor.convert2Do(userInfoModel)) > 0);
    }

    /**
     * @param userInfoModels
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult<Long> modifyUsersByIds(List<UserInfoModel> userInfoModels) {
        if (CollectionUtils.isEmpty(userInfoModels)) {
            return BaseResult.success(0L);
        }
        return BaseResult.success(userInfoModels.stream().map(this::modifyUserById).count());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<Boolean> disableUserById(Long id) {
        MuUserInfo muUserInfo = userInfoDao.selectById(id);
        muUserInfo.setStatus(Status.DISABLE.name());
        return BaseResult.success(userInfoDao.updateById(muUserInfo) > 0);
    }

    /**
     * @param uid
     * @return
     */
    @Override
    public BaseResult<Boolean> disableUserByUid(Long uid) {
        MuUserInfo muUserInfo = userInfoDao.selectOne(new LambdaQueryWrapper<MuUserInfo>().eq(MuUserInfo::getUserId, uid));
        muUserInfo.setStatus(Status.DISABLE.name());
        return BaseResult.success(userInfoDao.updateById(muUserInfo) > 0);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<Boolean> removeUserById(Long id) {
        return BaseResult.success(userInfoDao.deleteById(id) > 0);
    }

    /**
     * @param uid
     * @return
     */
    @Override
    public BaseResult<Boolean> removeUserByUid(Long uid) {
        return BaseResult.success(userInfoDao.delete(new LambdaQueryWrapper<MuUserInfo>().eq(MuUserInfo::getUserId, uid)) > 1);
    }

}
