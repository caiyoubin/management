package com.example.service;

import com.example.dao.CustomersEntity;
import com.example.dao.CustomersRepository;
import com.example.web.request.CustomersRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomersService extends BaseService<CustomersEntity> {

    @Autowired
    CustomersRepository repository;


    public void save(CustomersEntity entity) {
        repository.save(entity);
    }

    public void update(CustomersRequest request) {
        final CustomersEntity entity = findById(request.getId());
        BeanUtils.copyProperties(request, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }
}