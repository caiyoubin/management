package com.example.service;

import com.example.dao.AccountsEntity;
import com.example.dao.AccountsRepository;
import com.example.web.exception.BadRequestException;
import com.example.web.request.AccountsRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class AccountsService extends BaseService<AccountsEntity> {

    @Autowired
    AccountsRepository repository;

    public void saveByItemName(String itemName) {
        AccountsEntity entity = repository.findByItemName(itemName);
        if (ObjectUtils.isEmpty(entity)) {
            entity = new AccountsEntity();
            entity.setItemName(itemName);
            repository.save(entity);
        }
    }

    public void update(AccountsRequest request) {
        final AccountsEntity entity = findById(request.getId());
        final String itemName = entity.getItemName();
        BeanUtils.copyProperties(request, entity);
        entity.setItemName(itemName);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    public List<AccountsEntity> finAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void save(AccountsRequest accountsRequest) {
        AccountsEntity entity = repository.findByItemName(accountsRequest.getItemName());
        if (entity != null) {
            throw new BadRequestException("项目名称已存在");
        }
        entity = new AccountsEntity();
        BeanUtils.copyProperties(accountsRequest, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    public int incomeSum() {
        return repository.incomeSum();
    }

    public List<Map<String, Object>> incomeMonth() {
        return repository.incomeMonth();
    }
}
