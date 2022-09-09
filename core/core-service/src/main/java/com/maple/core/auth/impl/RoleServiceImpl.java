package com.maple.core.auth.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.convert.RoleConvertor;
import com.maple.core.enums.Status;
import com.maple.core.exception.BizException;
import com.maple.core.model.db.RoleModel;
import com.maple.core.request.RolePageQuery;
import com.maple.core.auth.RoleService;
import com.maple.core.result.base.BaseResult;
import com.maple.dal.dao.MuRoleDao;
import com.maple.dal.entity.MuRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private MuRoleDao roleDao;

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<RoleModel> getRoleById(Long id) {
        return BaseResult.success(RoleConvertor.convert2Model(roleDao.selectById(id)));
    }

    /**
     * @param code
     * @return
     */
    @Override
    public BaseResult<RoleModel> getRoleByCode(String code) {
        return BaseResult.success(
                RoleConvertor.convert2Model(roleDao.selectOne(new LambdaQueryWrapper<MuRole>().eq(MuRole::getRoleCode, code))));
    }

    @Override
    public BaseResult<List<RoleModel>> getRolesByCodes(List<String> codes) {
        List<MuRole> muRoles = roleDao.selectList(new LambdaQueryWrapper<MuRole>().in(MuRole::getRoleCode, codes));
        return BaseResult.success(RoleConvertor.convert2Models(muRoles));
    }

    /**
     * @param rolePageQuery
     * @return
     */
    @Override
    public BaseResult<Page<RoleModel>> pageListRoles(RolePageQuery rolePageQuery) {
        LambdaQueryWrapper<MuRole> wrapper = new LambdaQueryWrapper<>();
        Page<MuRole> page = roleDao.selectPage(Page.of(rolePageQuery.getPageNum(), rolePageQuery.getPageSize()), wrapper);
        return BaseResult.success(RoleConvertor.convert2ModelPage(page));
    }

    /**
     * @param roleModel
     * @return
     */
    @Override
    public BaseResult<Boolean> addRole(RoleModel roleModel) {
        try {
            roleModel.checkAddInfo();
            MuRole muRole = RoleConvertor.convert2Do(roleModel);
            Assert.isTrue(roleDao.insert(muRole) > 0, () -> new BizException("添加角色失败", 1002));
            roleModel.setId(muRole.getId());
            return BaseResult.success(true);
        } catch (DuplicateKeyException e) {
            log.warn("已有重复角色, " + e.getMessage());
            return BaseResult.success(false, "已有重复用户, " + e.getMessage());
        }
    }

    /**
     * @param roleModel
     * @return
     */
    @Override
    public BaseResult<Boolean> modifyRoleById(RoleModel roleModel) {
        roleModel.checkModifyInfo();
        return BaseResult.success(roleDao.updateById(RoleConvertor.convert2Do(roleModel)) > 0);
    }

    /**
     * @param roleModels
     * @return
     */
    @Override
    @Transactional
    public BaseResult<Long> modifyRoleByIds(List<RoleModel> roleModels) {
        if (CollectionUtils.isEmpty(roleModels)) {
            return BaseResult.success(0L);
        }
        return BaseResult.success(roleModels.stream().map(this::modifyRoleById).count());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<Boolean> disableRoleById(Long id) {
        MuRole muRole = roleDao.selectById(id);
        muRole.setStatus(Status.DISABLE.name());
        return BaseResult.success(roleDao.updateById(muRole) > 0);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public BaseResult<Boolean> disableRoleByCode(String code) {
        MuRole muRole = roleDao.selectOne(new LambdaQueryWrapper<MuRole>().eq(MuRole::getRoleCode, code));
        muRole.setStatus(Status.DISABLE.name());
        return BaseResult.success(roleDao.updateById(muRole) > 0);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public BaseResult<Boolean> removeRoleById(Long id) {
        return BaseResult.success(roleDao.deleteById(id) > 0);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public BaseResult<Boolean> removeRoleByCode(String code) {
        return BaseResult.success(roleDao.delete(new LambdaQueryWrapper<MuRole>().eq(MuRole::getRoleCode, code)) > 0);
    }

}
