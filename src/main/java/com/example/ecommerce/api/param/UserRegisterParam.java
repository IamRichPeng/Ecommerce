package com.example.ecommerce.api.param;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
// autofill getter setter constructer

public class UserRegisterParam {

    @NotNull(message = "Name can't be empty")
    private String loginName;


    @NotNull(message = "Password can't be empty")
    private String password;
}
