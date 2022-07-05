package com.example.ecommerce.dao;

import com.example.ecommerce.entity.MallUser;
import org.springframework.data.repository.CrudRepository;

public interface MallUserMapper extends CrudRepository<MallUser, Long> {

    MallUser findMallUserByLoginName(String loginName);
    MallUser save(MallUser mallUser);
    MallUser findMallUserByLoginNameAndPassword(String LoginName, String password);
    MallUser findMallUserByUserId(Long userId);
}
