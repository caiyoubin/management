package com.example.service;

import com.example.dao.BusinessEntity;
import com.example.dao.BusinessRepository;
import com.example.web.request.BusinessUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusinessService extends BaseService<BusinessEntity> {

    @Autowired
    BusinessRepository repository;


    public void save(BusinessEntity entity) {
        repository.save(entity);
    }

    public void update(BusinessUpdateRequest request) {
        final BusinessEntity entity = findById(request.getId());
        BeanUtils.copyProperties(request, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    public List<BusinessEntity> findByAll() {
        return repository.findAll();
    }
}
