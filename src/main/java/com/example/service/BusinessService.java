package com.example.service;

import com.example.dao.*;
import com.example.web.exception.BadRequestException;
import com.example.web.request.BusinessRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BusinessService extends BaseService<BusinessEntity> {

    @Autowired
    BusinessRepository repository;
    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    AccountsRepository accountsRepository;

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
        final String newItemName = request.getItemName();
        final String oldItemName = entity.getItemName();
        BeanUtils.copyProperties(request, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);

        if (!oldItemName.equals(newItemName)) {
            List<BusinessEntity> entities = repository.findByItemName(oldItemName);
            entities.forEach(e -> e.setItemName(newItemName));
            repository.saveAll(entities);

            AccountsEntity accountsEntity = accountsRepository.findByItemName(oldItemName);
            accountsEntity.setItemName(newItemName);
            accountsRepository.save(accountsEntity);
        }
    }

    public List<BusinessEntity> findByAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(Integer id) {
        final BusinessEntity entity = findById(id);
        final String itemName = entity.getItemName();
        repository.deleteById(id);
        final List<BusinessEntity> entities = repository.findByItemName(itemName);
        if (ObjectUtils.isEmpty(entities)) {
            accountsRepository.deleteByItemName(itemName);
        }
    }

    public int orderCount() {
        return repository.orderCount();
    }

    public int currentMonthCustomers() {
        final Calendar calendar = Calendar.getInstance();
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int year = calendar.get(Calendar.YEAR);
        return repository.currentMonthCustomers(year, month);
    }

}
