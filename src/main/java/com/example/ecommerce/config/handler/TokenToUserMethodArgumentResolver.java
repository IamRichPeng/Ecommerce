package com.example.ecommerce.config.handler;

import com.example.ecommerce.common.MallException;
import com.example.ecommerce.common.ServiceResultEnum;
import com.example.ecommerce.config.annotation.TokenToUser;
import com.example.ecommerce.dao.MallUserMapper;
import com.example.ecommerce.dao.UserTokenMapper;
import com.example.ecommerce.entity.MallUser;
import com.example.ecommerce.entity.UserToken;
import com.example.ecommerce.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private MallUserMapper mallUserMapper;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser){
            //MallUser malluser = null

            String token = webRequest.getHeader("token");
            if(tokenUtil.isValidToken(token)){
                UserToken userToken = userTokenMapper.findUserTokenByToken(token);

                if(tokenUtil.isNotValidUserToken(userToken)){
                    MallException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }

                MallUser mallUser = mallUserMapper.findMallUserByUserId(userToken.getUserId());
                if(mallUser == null){
                    MallException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                return mallUser;
            }else{
                //log.info("logout process,token={}", token);
                MallException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }

        }

        return null;
    }

}
