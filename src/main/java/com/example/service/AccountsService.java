package com.example.service;

import com.example.dao.AccountsEntity;
import com.example.dao.AccountsRepository;
import com.example.web.request.AccountsRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class AccountsService extends BaseService<AccountsEntity> {

    @Autowired
    AccountsRepository repository;

    public void saveByItemId(String itemName) {
        AccountsEntity entity = repository.findByItemName(itemName);
        if (ObjectUtils.isEmpty(entity)) {
            entity = new AccountsEntity();
            entity.setItemName(itemName);
            repository.save(entity);
        }
    }

    public void update(AccountsRequest request) {
        final AccountsEntity entity = findById(request.getId());
        BeanUtils.copyProperties(request, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }
}
