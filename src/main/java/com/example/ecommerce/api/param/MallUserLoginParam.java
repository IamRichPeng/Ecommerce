package com.example.ecommerce.api.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MallUserLoginParam {

    @NotNull(message = "登录名不能为空")
    private String loginName;

    @NotNull(message = "密码不能为空")
    private String password;
}
