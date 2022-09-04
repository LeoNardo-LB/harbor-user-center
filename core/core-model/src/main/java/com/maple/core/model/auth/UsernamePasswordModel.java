package com.maple.core.model.auth;

import cn.hutool.core.lang.Assert;
import com.maple.core.exception.BizException;
import lombok.Data;

@Data
public class UsernamePasswordModel extends AuthenticateModel {

    private String password;

    /**
     * 登陆方式
     */
    private String authWay;

    @Override
    public void authenticateCheck() {
        Assert.notBlank(getCertificate(), () -> new BizException("用户名不能为空", 1002));
        Assert.notBlank(getPassword(), () -> new BizException("密码不能为空", 1002));
    }

    @Override
    protected void cleanAuthKey() {
        this.password = null;
    }

}
