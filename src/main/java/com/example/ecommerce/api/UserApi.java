package com.example.ecommerce.api;

import com.example.ecommerce.api.param.MallUserLoginParam;
import com.example.ecommerce.api.param.UserRegisterParam;
import com.example.ecommerce.api.param.UserUpdateParam;
import com.example.ecommerce.api.vo.UserVO;
import com.example.ecommerce.common.Constants;
import com.example.ecommerce.common.ServiceResultEnum;
import com.example.ecommerce.config.annotation.TokenToUser;
import com.example.ecommerce.entity.MallUser;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.NumberUtil;
import com.example.ecommerce.util.Result;
import com.example.ecommerce.util.ResultGenerator;
import com.example.ecommerce.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Slf4j
//logger
@RestController
@RequestMapping("/api/v1")
public class UserApi {

    @Resource
    private UserService userService;

    @PostMapping("/user/register")
    public Result register(@RequestBody @Valid UserRegisterParam userRegisterParam){
        if(!NumberUtil.isPhone(userRegisterParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String registerResult = userService.register(userRegisterParam.getLoginName(),userRegisterParam.getPassword());

        log.info("register api,loginName={},loginResult={}", userRegisterParam.getLoginName(), registerResult);

        //register success or not
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(registerResult);
    }


    @PostMapping("/user/login")
    public Result<String> login(@RequestBody @Valid MallUserLoginParam mallUserLoginParam){
        if(!NumberUtil.isPhone(mallUserLoginParam.getLoginName())){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = userService.login(mallUserLoginParam.getLoginName(), mallUserLoginParam.getPassword());

        log.info("login api,loginName={},loginResult={}", mallUserLoginParam.getLoginName(), loginResult);

       if (loginResult != null && !StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH){
           Result result = ResultGenerator.genSuccessResult();
           result.setData(loginResult);
           //save the token:loginResult
           return result;
       }

        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/user/logout")
    public Result<String> logout(@TokenToUser MallUser mallUser){
        Boolean logoutResult = userService.logout(mallUser.getUserId());
        log.info("logout api,loginMallUserId={}", mallUser.getUserId());
        if(logoutResult){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("logout error");

    }

    @PutMapping("/user/info")
    public Result updateInfo(@TokenToUser MallUser mallUser,@RequestBody UserUpdateParam userUpdateParam){
        Boolean updateResult = userService.updateUserInfo(userUpdateParam, mallUser.getUserId());
        if(updateResult){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("update error");

    }

    @GetMapping("/user/info")
    public Result<UserVO> getUserDetail(@TokenToUser MallUser mallUser){
        UserVO userVO = new UserVO();
        // we already get mallUser by @tokentouser, so we dont need to go to service
        BeanUtils.copyProperties(mallUser,userVO);
        return ResultGenerator.genSuccessResult(userVO);

    }

}
