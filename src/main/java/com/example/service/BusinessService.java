package com.example.service;

import com.example.dao.BusinessEntity;
import com.example.dao.BusinessRepository;
import com.example.dao.CustomersEntity;
import com.example.dao.CustomersRepository;
import com.example.web.exception.BadRequestException;
import com.example.web.request.BusinessRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusinessService extends BaseService<BusinessEntity> {

    @Autowired
    BusinessRepository repository;
    @Autowired
    CustomersRepository customersRepository;

    public void save(BusinessEntity entity) {
        final String customerName = entity.getCustomerName();
        final CustomersEntity customersEntity = customersRepository.findByCustomerName(customerName);
        if (customersEntity == null) {
            throw new BadRequestException(customerName + " 不存在于客户列表, 请前往添加.");
        }
        repository.save(entity);
    }

    public void update(BusinessRequest request) {
        final String customerName = request.getCustomerName();
        final CustomersEntity customersEntity = customersRepository.findByCustomerName(customerName);
        if (customersEntity == null) {
            throw new BadRequestException(customerName + " 不存在于客户列表, 请前往添加.");
        }
        final BusinessEntity entity = findById(request.getId());
        BeanUtils.copyProperties(request, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    public List<BusinessEntity> findByAll() {
        return repository.findAll();
    }
}
