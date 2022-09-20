package com.example.service;

import com.example.dao.UsersEntity;
import com.example.dao.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService<UsersEntity> {

    @Autowired
    UsersRepository repository;


    public UsersEntity findByUserIdAndPassword(String userId, String password) {
        return repository.findByUserIdAndPassword(userId, password);
    }
}