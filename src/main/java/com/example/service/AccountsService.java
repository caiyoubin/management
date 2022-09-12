package com.example.service;

import com.example.dao.AccountsEntity;
import com.example.dao.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AccountsService extends BaseService<AccountsEntity> {

    @Autowired
    AccountsRepository repository;

    public void saveByItemId(String itemId) {
        AccountsEntity entity = repository.findByItemId(itemId);
        if (ObjectUtils.isEmpty(entity)) {
            entity = new AccountsEntity();
            entity.setItemId(itemId);
            repository.save(entity);
        }
    }
}
