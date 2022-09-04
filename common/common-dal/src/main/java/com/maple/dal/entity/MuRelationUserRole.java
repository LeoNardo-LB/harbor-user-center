package com.maple.dal.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 用户_角色关系表(MuRelationUserRole)表实体类
 * @author makejava
 * @since 2022-09-04 00:40:46
 */
@SuppressWarnings("serial")
public class MuRelationUserRole extends Model<MuRelationUserRole> {
    //主键id
    private Long id;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //修改人信息
    private Long modifierid;

    private String status;
    //用户id
    private String userId;
    //角色code
    private String roleCode;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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

