package com.maple.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.maple.core.model.db.AuthorityModel;
import com.maple.dal.entity.MuAuthority;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorityConvertor {

    /**
     * MuAuthority 2 AuthorityModel
     * @param muAuthority
     * @return
     */
    public static AuthorityModel convert2Model(MuAuthority muAuthority) {
        if (Objects.isNull(muAuthority)) {
            return null;
        }
        AuthorityModel authorityModel = new AuthorityModel();
        BeanUtils.copyProperties(muAuthority, authorityModel);
        return authorityModel;
    }

    public static List<AuthorityModel> convert2Models(List<MuAuthority> muAuthoritys) {
        if (CollectionUtils.isEmpty(muAuthoritys)) {
            return Lists.newArrayList();
        }
        return muAuthoritys.stream().map(AuthorityConvertor::convert2Model).collect(Collectors.toList());
    }

    /**
     * Page<MuAuthority> 2 Page<AuthorityModel>
     * @param page
     * @return
     */
    public static Page<AuthorityModel> convert2ModelPage(Page<MuAuthority> page) {
        if (Objects.isNull(page)) {
            return null;
        }
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        }
        Page<AuthorityModel> target = new Page<>();
        BeanUtils.copyProperties(page, target);
        target.setRecords(convert2Models(page.getRecords()));
        return target;
    }

    /**
     * AuthorityModel 2 MuAuthority
     * @param authorityModel
     * @return
     */
    public static MuAuthority convert2Do(AuthorityModel authorityModel) {
        if (Objects.isNull(authorityModel)) {
            return null;
        }
        MuAuthority muAuthority = new MuAuthority();
        BeanUtils.copyProperties(authorityModel, muAuthority);
        return muAuthority;
    }

}
