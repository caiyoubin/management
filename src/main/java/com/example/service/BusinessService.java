package com.example.service;

import com.example.dao.BusinessEntity;
import com.example.dao.BusinessRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class
BusinessService {

    @Resource
    BusinessRepository repository;

    public void save(BusinessEntity entity) {
        repository.save(entity);
    }
}
