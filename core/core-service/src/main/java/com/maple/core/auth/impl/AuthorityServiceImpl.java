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
import com.maple.core.result.base.BaseResult;
import com.maple.dal.dao.MuAuthorityDao;
import com.maple.dal.entity.MuAuthority;
import com.maple.utils.AroundLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private MuAuthorityDao authorityDao;

    /**
     * @param id
     * @return
     */
    @Override
    @AroundLog
    public BaseResult<AuthorityModel> getAuthorityById(Long id) {
        return BaseResult.success(AuthorityConvertor.convert2Model(authorityDao.selectById(id)));
    }

    /**
     * @param code
     * @return
     */
    @Override
    @AroundLog
    public BaseResult<AuthorityModel> getAuthorityByCode(String code) {
        return BaseResult.success(AuthorityConvertor.convert2Model(
                authorityDao.selectOne(new LambdaQueryWrapper<MuAuthority>().eq(MuAuthority::getAuthorityCode, code))));
    }

    @Override
    @AroundLog
    public BaseResult<List<AuthorityModel>> getAuthoritiesByCodes(List<String> codes) {
        List<MuAuthority> muAuthoritys = authorityDao.selectList(
                new LambdaQueryWrapper<MuAuthority>().in(MuAuthority::getAuthorityCode, codes));
        return BaseResult.success(AuthorityConvertor.convert2Models(muAuthoritys));
    }

    /**
     * @param authorityPageQuery
     * @return
     */
    @Override
    public BaseResult<Page<AuthorityModel>> pageListAuthorities(AuthorityPageQuery authorityPageQuery) {
        LambdaQueryWrapper<MuAuthority> wrapper = new LambdaQueryWrapper<>();
        Page<MuAuthority> page = authorityDao.selectPage(Page.of(authorityPageQuery.getPageNum(), authorityPageQuery.getPageSize()),
                wrapper);
        return BaseResult.success(AuthorityConvertor.convert2ModelPage(page));
    }

    /**
     * @param authorityModel
     * @return
     */
    @Override
    public BaseResult<Boolean> addAuthority(AuthorityModel authorityModel) {
        try {
            authorityModel.checkAddInfo();
            MuAuthority muAuthority = AuthorityConvertor.convert2Do(authorityModel);
            Assert.isTrue(authorityDao.insert(muAuthority) > 0, () -> new BizException("添加角色失败", 1002));
            authorityModel.setId(muAuthority.getId());
            return BaseResult.success(true);
        } catch (DuplicateKeyException e) {
            log.warn("已有重复角色, " + e.getMessage());
            return BaseResult.success(false, "已有重复用户, " + e.getMessage());
        }
    }

    /**
     * @param authorityModel
     * @return
     */
    @Override
    public BaseResult<Boolean> modifyAuthorityById(AuthorityModel authorityModel) {
        authorityModel.checkModifyInfo();
        return BaseResult.success(authorityDao.updateById(AuthorityConvertor.convert2Do(authorityModel)) > 0);
    }

    /**
     * @param authorityModels
     * @return
     */
    @Override
    @Transactional
    public BaseResult<Long> modifyAuthorityByIds(List<AuthorityModel> authorityModels) {
        if (CollectionUtils.isEmpty(authorityModels)) {
            return BaseResult.success(0L);
        }
        return BaseResult.success(authorityModels.stream().map(this::modifyAuthorityById).count());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<Boolean> disableAuthorityById(Long id) {
        MuAuthority muAuthority = authorityDao.selectById(id);
        muAuthority.setStatus(Status.DISABLE.name());
        return BaseResult.success(authorityDao.updateById(muAuthority) > 0);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public BaseResult<Boolean> disableAuthorityByCode(String code) {
        MuAuthority muAuthority = authorityDao.selectOne(new LambdaQueryWrapper<MuAuthority>().eq(MuAuthority::getAuthorityCode, code));
        muAuthority.setStatus(Status.DISABLE.name());
        return BaseResult.success(authorityDao.updateById(muAuthority) > 0);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<Boolean> removeAuthorityById(Long id) {
        return BaseResult.success(authorityDao.deleteById(id) > 0);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public BaseResult<Boolean> removeAuthorityByCode(String code) {
        return BaseResult.success(authorityDao.delete(new LambdaQueryWrapper<MuAuthority>().eq(MuAuthority::getAuthorityCode, code)) > 0);
    }

}
