package com.maple.portal.controller.auth;

import com.maple.core.model.auth.AuthenticateModel;
import com.maple.core.model.auth.UsernamePasswordModel;
import com.maple.core.result.MvcResult;
import com.maple.service.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/user")
public class WebLoginController {

    @Autowired
    @Qualifier("webLoginService")
    private LoginService loginService;

    @PostMapping("login")
    public MvcResult<String> login(@RequestBody UsernamePasswordModel model){
        return MvcResult.success(loginService.login(model));
    }

    @PostMapping("register")
    public MvcResult<Boolean> register(@RequestBody UsernamePasswordModel model){
        return MvcResult.success(loginService.register(model));
    }

    @DeleteMapping("logout")
    public MvcResult<Boolean> logout(@RequestBody UsernamePasswordModel model){
        return MvcResult.success(loginService.logout(model));
    }

}
