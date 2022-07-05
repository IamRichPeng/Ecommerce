package com.example.ecommerce.service.impl;

import com.example.ecommerce.api.param.UserUpdateParam;
import com.example.ecommerce.common.Constants;
import com.example.ecommerce.common.MallException;
import com.example.ecommerce.common.ServiceResultEnum;
import com.example.ecommerce.dao.MallUserMapper;
import com.example.ecommerce.dao.UserTokenMapper;
import com.example.ecommerce.entity.MallUser;
import com.example.ecommerce.entity.UserToken;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MallUserMapper mallUserMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public String register(String loginName, String password) {
        if(mallUserMapper.findMallUserByLoginName(loginName) != null){
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }

        MallUser registerUser = new MallUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign(Constants.USER_INTRO);
        //String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPassword(password);

        if (mallUserMapper.save(registerUser) != null){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String password) {
        MallUser user = mallUserMapper.findMallUserByLoginNameAndPassword(loginName,password);
       if (user!= null){
          // String token = getNewToken(System.currentTimeMillis() + "", user.getUserId());
          String token = "1234567890123456789012";

          //token expired time frame
          Date now = new Date();
          Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);

          UserToken userToken = userTokenMapper.findUserTokenByUserId(user.getUserId());
         // never login
          if(userToken == null){
              userToken = new UserToken();
              userToken.setUserId(user.getUserId());
              userToken.setToken(token);
              userToken.setUpdateTime(now);
              userToken.setExpireTime(expireTime);

              if(userTokenMapper.save(userToken) != null){
                  return token;
              }
          } else {
              userToken.setToken(token);
              userToken.setUpdateTime(now);
              userToken.setExpireTime(expireTime);
              if(userTokenMapper.save(userToken) != null){
                  return token;
              }
          }

       }

        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public Boolean logout(Long userID) {
        userTokenMapper.deleteById(userID);
        return true;
    }

    @Override
    public Boolean updateUserInfo(UserUpdateParam userUpdateParam, Long userId) {
        MallUser mallUser = mallUserMapper.findMallUserByUserId(userId);

        if(mallUser == null){
            MallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }

        return false;
    }


}
