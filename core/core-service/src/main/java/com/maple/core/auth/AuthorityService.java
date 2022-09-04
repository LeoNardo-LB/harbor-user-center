package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.AuthorityModel;
import com.maple.core.request.AuthorityPageQuery;

import java.util.List;

public interface AuthorityService {

    AuthorityModel getAuthorityById(Long id);

    AuthorityModel getAuthorityByCode(String code);

    /**
     * 通过 AuthorityCodes 批量获取
     * @param codes
     * @return
     */
    List<AuthorityModel> getAuthoritiesByCodes(List<String> codes);

    Page<AuthorityModel> pageListAuthorities(AuthorityPageQuery AuthorityPageQuery);

    Boolean addAuthority(AuthorityModel authorityModel);

    Boolean modifyAuthorityById(AuthorityModel authorityModel);

    Long modifyAuthorityByIds(List<AuthorityModel> authorityModels);

    Boolean disableAuthorityById(Long id);

    Boolean disableAuthorityByCode(String code);

    Boolean removeAuthorityById(Long id);

    Boolean removeAuthorityByCode(String code);

}
