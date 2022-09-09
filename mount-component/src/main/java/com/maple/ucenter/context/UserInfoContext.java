package com.maple.ucenter.context;

import com.maple.ucenter.entity.AuthModel;

public class UserInfoContext {

    private static final InheritableThreadLocal<AuthModel> AUTH_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void setAuthModel(AuthModel authModel) {
        AUTH_THREAD_LOCAL.set(authModel);
    }

    public static AuthModel getAuthModel() {
        return AUTH_THREAD_LOCAL.get();
    }

}
