package com.maple.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.maple.core.model.db.UserInfoModel;
import com.maple.dal.entity.MuUserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserInfoConvertor {

    /**
     * MuUserInfo 转换为 UserInfoModel
     * @param userInfo
     * @return
     */
    public static UserInfoModel convert2Model(MuUserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            return null;
        }
        UserInfoModel model = new UserInfoModel();
        BeanUtils.copyProperties(userInfo, model);
        return model;
    }

    /**
     * List<MuUserInfo> 2 List<UserInfoModel>
     * @return
     */
    public static List<UserInfoModel> convert2Models(List<MuUserInfo> muUserInfos){
        if(CollectionUtils.isEmpty(muUserInfos)){
            return Lists.newArrayList();
        }
        return muUserInfos.stream().map(UserInfoConvertor::convert2Model).collect(Collectors.toList());
    }

    /**
     * Page<MuUserInfo> 2 Page<UserInfoModel>
     * @param page
     * @return
     */
    public static Page<UserInfoModel> convert2ModelPage(Page<MuUserInfo> page) {
        if (Objects.isNull(page)) {
            return null;
        }
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        }
        Page<UserInfoModel> target = new Page<>();
        BeanUtils.copyProperties(page, target);
        target.setRecords(convert2Models(page.getRecords()));
        return target;
    }

    /**
     * UserInfoModel 2 MuUserInfo
     * @param userInfoModel
     * @return
     */
    public static MuUserInfo convert2Do(UserInfoModel userInfoModel) {
        if(Objects.isNull(userInfoModel)) {
            return null;
        }
        MuUserInfo muUserInfo = new MuUserInfo();
        BeanUtils.copyProperties(userInfoModel, muUserInfo);
        return muUserInfo;
    }

}
