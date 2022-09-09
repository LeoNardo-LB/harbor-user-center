package com.maple.ucenter.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthModel {

    List<String> getRoles();

    List<String> getAuthorities();

    /**
     * 是否已到期
     * @return
     */
    boolean expired();

}
