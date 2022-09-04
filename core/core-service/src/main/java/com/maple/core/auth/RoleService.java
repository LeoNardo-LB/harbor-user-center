package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.RoleModel;
import com.maple.core.request.RolePageQuery;

import java.util.List;

public interface RoleService {

    RoleModel getRoleById(Long id);

    RoleModel getRoleByCode(String code);

    /**
     * 通过roleCodes 批量获取
     * @param codes
     * @return
     */
    List<RoleModel> getRolesByCodes(List<String> codes);

    Page<RoleModel> pageListRoles(RolePageQuery rolePageQuery);

    Boolean addRole(RoleModel roleModel);

    Boolean modifyRoleById(RoleModel roleModel);

    Long modifyRoleByIds(List<RoleModel> roleModels);

    Boolean disableRoleById(Long id);

    Boolean disableRoleByCode(String code);

    Boolean removeRoleById(Long id);

    Boolean removeRoleByCode(String code);

}
