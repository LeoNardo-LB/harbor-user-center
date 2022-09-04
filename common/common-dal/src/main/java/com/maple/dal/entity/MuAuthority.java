package com.maple.dal.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 权限表(MuAuthority)表实体类
 * @author makejava
 * @since 2022-09-04 00:40:45
 */
@SuppressWarnings("serial")
public class MuAuthority extends Model<MuAuthority> {
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

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
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

