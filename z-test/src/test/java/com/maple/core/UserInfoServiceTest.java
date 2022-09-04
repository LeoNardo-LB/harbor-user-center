package com.maple.core;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.ServiceTestBase;
import com.maple.core.model.db.UserInfoModel;
import com.maple.core.request.UserPageQuery;
import com.maple.core.auth.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoServiceTest extends ServiceTestBase {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void test_Crud(){
        showPage(1,10);

        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setAccount(RandomUtil.randomString(10));
        userInfoModel.setPassword("12321");
        System.out.println(userInfoService.addUser(userInfoModel));
        UserInfoModel userInfoModel2 = new UserInfoModel();
        userInfoModel2.setAccount(RandomUtil.randomString(10));
        userInfoModel2.setPassword("12321");
        System.out.println(userInfoService.addUser(userInfoModel2));
        showPage(1,10);
    }

    private void showPage(int pageNum, int pageSize) {
        UserPageQuery query = new UserPageQuery();
        query.setPageNum(pageNum);
        query.setPageSize(pageSize);
        Page<UserInfoModel> page = userInfoService.pageListUsers(query);
        System.out.println(page);
    }

}
