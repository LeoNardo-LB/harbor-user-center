package com.maple.core.auth.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.convert.AuthorityConvertor;
import com.maple.core.enums.Status;
import com.maple.core.exception.BizException;
import com.maple.core.model.db.AuthorityModel;
import com.maple.core.request.AuthorityPageQuery;
import com.maple.core.auth.AuthorityService;
import com.maple.dal.dao.MuAuthorityDao;
import com.maple.dal.entity.MuAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private MuAuthorityDao authorityDao;

    /**
     * @param id
     * @return
     */
    @Override
    public AuthorityModel getAuthorityById(Long id) {
        return AuthorityConvertor.convert2Model(authorityDao.selectById(id));
    }

    /**
     * @param code
     * @return
     */
    @Override
    public AuthorityModel getAuthorityByCode(String code) {
        return AuthorityConvertor.convert2Model(authorityDao.selectOne(new LambdaQueryWrapper<MuAuthority>().eq(MuAuthority::getAuthorityCode, code)));
    }

    @Override
    public List<AuthorityModel> getAuthoritiesByCodes(List<String> codes) {
        List<MuAuthority> muAuthoritys = authorityDao.selectList(new LambdaQueryWrapper<MuAuthority>().in(MuAuthority::getAuthorityCode, codes));
        return AuthorityConvertor.convert2Models(muAuthoritys);
    }

    /**
     * @param authorityPageQuery
     * @return
     */
    @Override
    public Page<AuthorityModel> pageListAuthorities(AuthorityPageQuery authorityPageQuery) {
        LambdaQueryWrapper<MuAuthority> wrapper = new LambdaQueryWrapper<>();
        Page<MuAuthority> page = authorityDao.selectPage(Page.of(authorityPageQuery.getPageNum(), authorityPageQuery.getPageSize()), wrapper);
        return AuthorityConvertor.convert2ModelPage(page);
    }

    /**
     * @param authorityModel
     * @return
     */
    @Override
    public Boolean addAuthority(AuthorityModel authorityModel) {
        authorityModel.checkAddInfo();
        MuAuthority muAuthority = AuthorityConvertor.convert2Do(authorityModel);
        Assert.isTrue(authorityDao.insert(muAuthority) > 0, () -> new BizException("添加角色失败", 1002));
        authorityModel.setId(muAuthority.getId());
        return true;
    }

    /**
     * @param authorityModel
     * @return
     */
    @Override
    public Boolean modifyAuthorityById(AuthorityModel authorityModel) {
        authorityModel.checkModifyInfo();
        return authorityDao.updateById(AuthorityConvertor.convert2Do(authorityModel)) > 0;
    }

    /**
     * @param authorityModels
     * @return
     */
    @Override
    @Transactional
    public Long modifyAuthorityByIds(List<AuthorityModel> authorityModels) {
        if (CollectionUtils.isEmpty(authorityModels)) {
            return 0L;
        }
        return authorityModels.stream().map(this::modifyAuthorityById).count();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean disableAuthorityById(Long id) {
        MuAuthority muAuthority = authorityDao.selectById(id);
        muAuthority.setStatus(Status.DISABLE.name());
        return authorityDao.updateById(muAuthority) > 0;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Boolean disableAuthorityByCode(String code) {
        MuAuthority muAuthority = authorityDao.selectOne(new LambdaQueryWrapper<MuAuthority>().eq(MuAuthority::getAuthorityCode, code));
        muAuthority.setStatus(Status.DISABLE.name());
        return authorityDao.updateById(muAuthority) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean removeAuthorityById(Long id) {
        return authorityDao.deleteById(id) > 0;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Boolean removeAuthorityByCode(String code) {
        return authorityDao.delete(new LambdaQueryWrapper<MuAuthority>().eq(MuAuthority::getAuthorityCode, code)) > 0;
    }

}
