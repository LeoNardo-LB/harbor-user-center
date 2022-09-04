package com.maple.core.model.db;

import cn.hutool.core.lang.Assert;
import com.maple.core.exception.BizException;
import com.maple.core.model.base.DbModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

@Data
public class AuthorityModel implements DbModel {

    //主键id
    private Long id;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //修改人信息
    private Long modifierid;

    private String status;
    //权限code
    private String authorityCode;
    //权限名称
    private String authorityName;
    //权限说明
    private String authorityDesc;

    @Override
    public void checkAddInfo() {
        Assert.isTrue(StringUtils.isNotBlank(authorityCode) && StringUtils.isNotBlank(authorityName),
                () -> new BizException("角色code与角色名称不能为空", 1002));
    }

    @Override
    public void checkModifyInfo() {
        Assert.isTrue(Objects.nonNull(id), () -> new BizException("主键id不能为空", 1002));
    }

}
