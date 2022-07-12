package com.example.ecommerce.util;

import com.example.ecommerce.common.Constants;
import com.example.ecommerce.entity.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    public boolean isValidToken(String token){
        if(token != null && !StringUtils.isEmpty(token) && token.length() == Constants.TOKEN_LENGTH){
            return true;
        }
        return false;
    }

    public boolean isValidUserToken(UserToken userToken){
        if ( null != userToken && userToken.getExpireTime().getTime() >= System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    public boolean isNotValidUserToken(UserToken userToken){
        return !isValidUserToken(userToken);
    }


}
