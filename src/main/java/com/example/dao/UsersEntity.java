package com.example.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity{

    private String username;
    private String password;
    private String nickname;
    private Boolean gender = true;
    private Boolean isEdit;
    private Boolean isAdmin;
    private Boolean state;
    private Date entryTime;
    private Date departureTime;

}
