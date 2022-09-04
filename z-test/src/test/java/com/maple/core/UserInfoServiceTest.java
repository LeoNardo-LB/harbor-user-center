package com.maple.core;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.ServiceTestBase;
import com.maple.core.model.db.UserModel;
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

        UserModel userModel = new UserModel();
        userModel.setAccount(RandomUtil.randomString(10));
        userModel.setPassword("12321");
        System.out.println(userInfoService.addUser(userModel));
        UserModel userModel2 = new UserModel();
        userModel2.setAccount(RandomUtil.randomString(10));
        userModel2.setPassword("12321");
        System.out.println(userInfoService.addUser(userModel2));
        showPage(1,10);
    }

    private void showPage(int pageNum, int pageSize) {
        UserPageQuery query = new UserPageQuery();
        query.setPageNum(pageNum);
        query.setPageSize(pageSize);
        Page<UserModel> page = userInfoService.pageListUsers(query);
        System.out.println(page);
    }

}
