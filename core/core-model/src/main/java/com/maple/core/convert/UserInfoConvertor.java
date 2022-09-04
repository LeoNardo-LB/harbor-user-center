package com.maple.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.maple.core.model.db.UserModel;
import com.maple.dal.entity.MuUserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserInfoConvertor {

    /**
     * MuUserInfo 转换为 UserModel
     * @param userInfo
     * @return
     */
    public static UserModel convert2Model(MuUserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            return null;
        }
        UserModel model = new UserModel();
        BeanUtils.copyProperties(userInfo, model);
        return model;
    }

    /**
     * List<MuUserInfo> 2 List<UserModel>
     * @return
     */
    public static List<UserModel> convert2Models(List<MuUserInfo> muUserInfos){
        if(CollectionUtils.isEmpty(muUserInfos)){
            return Lists.newArrayList();
        }
        return muUserInfos.stream().map(UserInfoConvertor::convert2Model).collect(Collectors.toList());
    }

    /**
     * Page<MuUserInfo> 2 Page<UserModel>
     * @param page
     * @return
     */
    public static Page<UserModel> convert2ModelPage(Page<MuUserInfo> page) {
        if (Objects.isNull(page)) {
            return null;
        }
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        }
        Page<UserModel> target = new Page<>();
        BeanUtils.copyProperties(page, target);
        target.setRecords(convert2Models(page.getRecords()));
        return target;
    }

    /**
     * UserModel 2 MuUserInfo
     * @param userModel
     * @return
     */
    public static MuUserInfo convert2Do(UserModel userModel) {
        if(Objects.isNull(userModel)) {
            return null;
        }
        MuUserInfo muUserInfo = new MuUserInfo();
        BeanUtils.copyProperties(userModel, muUserInfo);
        return muUserInfo;
    }

}
