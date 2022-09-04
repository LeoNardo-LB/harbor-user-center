package com.maple.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.maple.core.model.db.RoleModel;
import com.maple.dal.entity.MuRole;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoleConvertor {

    /**
     * MuRole 2 RoleModel
     * @param muRole
     * @return
     */
    public static RoleModel convert2Model(MuRole muRole) {
        if(Objects.isNull(muRole)){
            return null;
        }
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(muRole,roleModel);
        return roleModel;
    }

    public static List<RoleModel> convert2Models(List<MuRole> muRoles){
        if (CollectionUtils.isEmpty(muRoles)) {
            return Lists.newArrayList();
        }
        return muRoles.stream().map(RoleConvertor::convert2Model).collect(Collectors.toList());
    }

    /**
     * Page<MuRole> 2 Page<RoleModel>
     * @param page
     * @return
     */
    public static Page<RoleModel> convert2ModelPage(Page<MuRole> page) {
        if (Objects.isNull(page)) {
            return null;
        }
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        }
        Page<RoleModel> target = new Page<>();
        BeanUtils.copyProperties(page, target);
        target.setRecords(convert2Models(page.getRecords()));
        return target;
    }

    /**
     * RoleModel 2 MuRole
     * @param roleModel
     * @return
     */
    public static MuRole convert2Do(RoleModel roleModel) {
        if(Objects.isNull(roleModel)){
            return null;
        }
        MuRole muRole = new MuRole();
        BeanUtils.copyProperties(roleModel, muRole);
        return muRole;
    }

}
