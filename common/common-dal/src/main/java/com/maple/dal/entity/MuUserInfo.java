package com.maple.dal.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 账号信息表(MuUserInfo)表实体类
 * @author makejava
 * @since 2022-09-04 00:40:46
 */
@SuppressWarnings("serial")
public class MuUserInfo extends Model<MuUserInfo> {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifierid() {
        return modifierid;
    }

    public void setModifierid(Long modifierid) {
        this.modifierid = modifierid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

