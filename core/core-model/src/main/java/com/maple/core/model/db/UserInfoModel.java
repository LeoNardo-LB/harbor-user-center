package com.maple.core.model.db;

import cn.hutool.core.lang.Assert;
import com.maple.core.exception.BizException;
import com.maple.core.model.base.DbModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

@Data
public class UserInfoModel implements DbModel {

    //主键id
    private Long id;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //修改人信息
    private Long modifierid;

    private String  status;
    //用户id
    private Long    userId;
    //账号
    private String  account;
    //手机号
    private String  phoneNumber;
    //邮箱
    private String  email;
    //密码
    private String  password;
    //头像
    private String  avatar;
    //昵称
    private String  nickname;
    //性别
    private Integer gender;
    //年龄
    private Integer age;
    //地区
    private String  area;
    //最后登陆时间
    private Date    lastLoginTime;

    /**
     * 添加校验
     */
    @Override
    public void checkAddInfo() {
        Assert.isTrue(StringUtils.isNotBlank(account) || StringUtils.isNotBlank(email) || StringUtils.isNotBlank(phoneNumber),
                () -> new BizException("添加用户信息验证失败, 账户/邮件/手机号不能同时为空!", 1002));
        Assert.isTrue(StringUtils.isNotBlank(password), () -> new BizException("添加用户信息验证失败, 密码不能为空", 1002));

    }

    /**
     * 修改校验
     */
    @Override
    public void checkModifyInfo() {
        Assert.isTrue(Objects.nonNull(id), () -> new BizException("修改用户信息验证失败, 主键id不能为null", 1002));
    }

}
