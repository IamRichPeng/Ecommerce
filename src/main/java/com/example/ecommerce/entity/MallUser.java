package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
//(ORM:Hibernate) for database "MallUser" table
public class MallUser {
    @Id
    @GeneratedValue
    private Long userId;

    private String nickName;

    private String loginName;

    private String password;

    private String introduceSign;

    private Byte isDeleted;

    private Byte lockedFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
