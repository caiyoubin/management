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
    AccountsRepository accountsRepository;

    public void saveByItemName(String itemName) {
        AccountsEntity entity = accountsRepository.findByItemName(itemName);
        if (ObjectUtils.isEmpty(entity)) {
            entity = new AccountsEntity();
            entity.setItemName(itemName);
            accountsRepository.save(entity);
        }
    }

    public void update(AccountsRequest request) {
        final AccountsEntity entity = findById(request.getId());
        final String itemName = entity.getItemName();
        BeanUtils.copyProperties(request, entity);
        entity.setItemName(itemName);
        entity.setUpdateTime(new Date());
        accountsRepository.save(entity);
    }

    public List<AccountsEntity> finAll() {
        return accountsRepository.findAll();
    }


    public void save(AccountsRequest accountsRequest) {
        AccountsEntity entity = accountsRepository.findByItemName(accountsRequest.getItemName());
        if (entity != null) {
            throw new BadRequestException("项目名称已存在");
        }
        entity = new AccountsEntity();
        BeanUtils.copyProperties(accountsRequest, entity);
        entity.setUpdateTime(new Date());
        accountsRepository.save(entity);
    }

    public int incomeSum() {
        return accountsRepository.incomeSum();
    }


    public Double[] incomeMonth() {
        final List<Map<String, Object>> maps = accountsRepository.incomeMonth();
        final int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        final Double[] ints = new Double[month];
        for (int i = 0; i < month; ++i) {
            ints[i] = 0.0;
        }
        for (Map<String, Object> map : maps) {
            final Integer key = (Integer)map.get("month");
            final Double value = (Double)map.get("price");
            ints[key-1] = value;
        }
        return ints;
    }
}
