package com.example.ecommerce.dao;

import com.example.ecommerce.entity.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenMapper extends CrudRepository<UserToken,Long> {
    UserToken findUserTokenByUserId(long userId);
    UserToken save(UserToken userToken);
    UserToken findUserTokenByToken(String token);
    void deleteById (Long userId);
}
