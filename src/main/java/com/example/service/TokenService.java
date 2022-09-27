package com.example.service;

import com.example.dao.TokenEntity;
import com.example.dao.TokenRepository;
import com.example.dao.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends BaseService<UsersEntity> {

    @Autowired
    TokenRepository tokenRepository;

    public void save(TokenEntity entity) {
        tokenRepository.save(entity);
    }
}
