package com.example.ecommerce.service;

import com.example.ecommerce.api.param.UserUpdateParam;

public interface UserService {
    String register(String loginName, String password);
    String login(String loginName, String password);
    Boolean logout(Long userId);
    Boolean updateUserInfo(UserUpdateParam userUpdateParam, Long userId);
}
