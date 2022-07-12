package com.example.ecommerce.api.param;

import lombok.Data;

@Data
public class UserUpdateParam {

    private String nickName;

    private String password;

    private String introduceSign;
}
