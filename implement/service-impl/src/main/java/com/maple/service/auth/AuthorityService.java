package com.maple.service.auth;

import java.util.List;
import java.util.Map;

public interface AuthorityService {

    /**
     * 获取这个凭证下的所有角色
     * @param certificate
     * @return
     */
    List<String> getRoles(String certificate);

    /**
     * 通过roleCode获取所有权限
     * @param roleCode
     * @return
     */
    List<String> getAuthoritiesByRole(String roleCode);

    /**
     * 通过roleCodes获取所有权限
     * @param roleCodes
     * @return
     */
    List<String> getAuthoritiesByRoles(List<String> roleCodes);

    /**
     * 获取这个凭证下所有权限
     * @param certificate
     * @return
     */
    List<String> getAuthorities(String certificate);

    /**
     * 权限鉴定
     * @return
     */
    Map<String, Boolean> checkAuthority(String certificate, List<String> authorities);

    /**
     * 角色是否存在
     * @param roleCode
     * @return
     */
    Boolean roleExists(String roleCode);

    /**
     * 权限是否存在
     * @param authorityCode
     * @return
     */
    Boolean authorityExists(String authorityCode);

}
