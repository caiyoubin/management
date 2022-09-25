package com.example.service;

import com.example.dao.CustomersEntity;
import com.example.dao.CustomersRepository;
import com.example.web.exception.BadRequestException;
import com.example.web.request.CustomersRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class CustomersService extends BaseService<CustomersEntity> {

    @Autowired
    CustomersRepository repository;

    public boolean checkCustomerName(String name) {
        final CustomersEntity entity = findByCustomerName(name);
        return !ObjectUtils.isEmpty(entity);
    }

    public void create(CustomersEntity entity) {
        final String customerName = entity.getCustomerName();
        if (checkCustomerName(customerName)) {
            throw new BadRequestException(customerName + " 已存在");
        }
        repository.save(entity);
    }


    public void update(CustomersRequest request) {
        final CustomersEntity entity = findById(request.getId());
        final String newName = request.getCustomerName();
        final String oldName = entity.getCustomerName();
        if (!newName.equalsIgnoreCase(oldName)) {
            if (checkCustomerName(newName)) {
                throw new BadRequestException(newName + " 已存在");
            }
        }
        BeanUtils.copyProperties(request, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    public CustomersEntity findByCustomerName(String customerName) {
        return repository.findByCustomerName(customerName);
    }

    public List<CustomersEntity> finAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public int countCustomers() {
        final List<CustomersEntity> all = repository.findAll();
        return all.size();
    }
}
