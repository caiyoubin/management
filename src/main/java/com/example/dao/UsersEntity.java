package com.example.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity{

    private String username;
    private String password;
    private String nickname;
    private String telephone;
    private Boolean edit;
    private Boolean admin;

}
