package com.maple.core.auth.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.core.convert.RoleConvertor;
import com.maple.core.enums.Status;
import com.maple.core.exception.BizException;
import com.maple.core.model.auth.AuthenticateModel;
import com.maple.core.model.auth.UsernamePasswordModel;
import com.maple.core.model.db.RoleModel;
import com.maple.core.request.RolePageQuery;
import com.maple.core.auth.RoleService;
import com.maple.dal.dao.MuRoleDao;
import com.maple.dal.entity.MuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private MuRoleDao roleDao;

    /**
     * @param id
     * @return
     */
    @Override
    public RoleModel getRoleById(Long id) {
        return RoleConvertor.convert2Model(roleDao.selectById(id));
    }

    /**
     * @param code
     * @return
     */
    @Override
    public RoleModel getRoleByCode(String code) {
        return RoleConvertor.convert2Model(roleDao.selectOne(new LambdaQueryWrapper<MuRole>().eq(MuRole::getRoleCode, code)));
    }

    @Override
    public List<RoleModel> getRolesByCodes(List<String> codes) {
        List<MuRole> muRoles = roleDao.selectList(new LambdaQueryWrapper<MuRole>().in(MuRole::getRoleCode, codes));
        return RoleConvertor.convert2Models(muRoles);
    }

    /**
     * @param rolePageQuery
     * @return
     */
    @Override
    public Page<RoleModel> pageListRoles(RolePageQuery rolePageQuery) {
        LambdaQueryWrapper<MuRole> wrapper = new LambdaQueryWrapper<>();
        Page<MuRole> page = roleDao.selectPage(Page.of(rolePageQuery.getPageNum(), rolePageQuery.getPageSize()), wrapper);
        return RoleConvertor.convert2ModelPage(page);
    }

    /**
     * @param roleModel
     * @return
     */
    @Override
    public Boolean addRole(RoleModel roleModel) {
        roleModel.checkAddInfo();
        MuRole muRole = RoleConvertor.convert2Do(roleModel);
        Assert.isTrue(roleDao.insert(muRole) > 0, () -> new BizException("添加角色失败", 1002));
        roleModel.setId(muRole.getId());
        return true;
    }

    /**
     * @param roleModel
     * @return
     */
    @Override
    public Boolean modifyRoleById(RoleModel roleModel) {
        roleModel.checkModifyInfo();
        return roleDao.updateById(RoleConvertor.convert2Do(roleModel)) > 0;
    }

    /**
     * @param roleModels
     * @return
     */
    @Override
    @Transactional
    public Long modifyRoleByIds(List<RoleModel> roleModels) {
        if (CollectionUtils.isEmpty(roleModels)) {
            return 0L;
        }
        return roleModels.stream().map(this::modifyRoleById).count();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean disableRoleById(Long id) {
        MuRole muRole = roleDao.selectById(id);
        muRole.setStatus(Status.DISABLE.name());
        return roleDao.updateById(muRole) > 0;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Boolean disableRoleByCode(String code) {
        MuRole muRole = roleDao.selectOne(new LambdaQueryWrapper<MuRole>().eq(MuRole::getRoleCode, code));
        muRole.setStatus(Status.DISABLE.name());
        return roleDao.updateById(muRole) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean removeRoleById(Long id) {
        return roleDao.deleteById(id) > 0;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Boolean removeRoleByCode(String code) {
        return roleDao.delete(new LambdaQueryWrapper<MuRole>().eq(MuRole::getRoleCode, code)) > 0;
    }

}
