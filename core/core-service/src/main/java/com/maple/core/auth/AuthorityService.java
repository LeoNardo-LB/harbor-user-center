package com.maple.core.auth;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.model.db.AuthorityModel;
import com.maple.core.request.AuthorityPageQuery;
import com.maple.core.result.base.BaseResult;

import java.util.List;

public interface AuthorityService {

    BaseResult<AuthorityModel> getAuthorityById(Long id);

    BaseResult<AuthorityModel> getAuthorityByCode(String code);

    /**
     * 通过 AuthorityCodes 批量获取
     * @param codes
     * @return
     */
    BaseResult<List<AuthorityModel>> getAuthoritiesByCodes(List<String> codes);

    BaseResult<Page<AuthorityModel>> pageListAuthorities(AuthorityPageQuery AuthorityPageQuery);

    BaseResult<Boolean> addAuthority(AuthorityModel authorityModel);

    BaseResult<Boolean> modifyAuthorityById(AuthorityModel authorityModel);

    BaseResult<Long> modifyAuthorityByIds(List<AuthorityModel> authorityModels);

    BaseResult<Boolean> disableAuthorityById(Long id);

    BaseResult<Boolean> disableAuthorityByCode(String code);

    BaseResult<Boolean> removeAuthorityById(Long id);

    BaseResult<Boolean> removeAuthorityByCode(String code);

}
