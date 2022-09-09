package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.RoleModel;
import com.maple.core.request.RolePageQuery;
import com.maple.core.result.base.BaseResult;

import java.util.List;

public interface RoleService {

    BaseResult<RoleModel> getRoleById(Long id);

    BaseResult<RoleModel> getRoleByCode(String code);

    /**
     * 通过roleCodes 批量获取
     * @param codes
     * @return
     */
    BaseResult<List<RoleModel>> getRolesByCodes(List<String> codes);

    BaseResult<Page<RoleModel>> pageListRoles(RolePageQuery rolePageQuery);

    BaseResult<Boolean> addRole(RoleModel roleModel);

    BaseResult<Boolean> modifyRoleById(RoleModel roleModel);

    BaseResult<Long> modifyRoleByIds(List<RoleModel> roleModels);

    BaseResult<Boolean> disableRoleById(Long id);

    BaseResult<Boolean> disableRoleByCode(String code);

    BaseResult<Boolean> removeRoleById(Long id);

    BaseResult<Boolean> removeRoleByCode(String code);

}
