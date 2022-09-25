package com.example.service;

import com.example.dao.UsersEntity;
import com.example.dao.UsersRepository;
import com.example.web.request.UserUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsersService extends BaseService<UsersEntity> {

    @Autowired
    UsersRepository repository;


    public UsersEntity findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    public UsersEntity findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void save(UsersEntity entity) {
        repository.save(entity);
    }

    public List<UsersEntity> finAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        final UsersEntity entity = findById(id);
        final String username = entity.getUsername();
        if (username.equalsIgnoreCase("Admin")) {
            return;
        }
        repository.deleteById(id);
    }

    public void update(UserUpdateRequest userRequest) {
        final String username = userRequest.getUsername();
        if (username.equalsIgnoreCase("admin")) {
            return;
        }
        final UsersEntity entity = findById(userRequest.getId());
        BeanUtils.copyProperties(userRequest, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }
}
