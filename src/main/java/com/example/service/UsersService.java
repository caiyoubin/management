package com.example.service;

import com.example.dao.UsersEntity;
import com.example.dao.UsersRepository;
import com.example.web.exception.BadRequestException;
import com.example.web.request.UserCreateRequest;
import com.example.web.request.UserUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class UsersService extends BaseService<UsersEntity> {

    @Autowired
    UsersRepository repository;

    public boolean checkUsername(String name) {
        final UsersEntity entity = findByUsername(name);
        return !ObjectUtils.isEmpty(entity);
    }

    public boolean checkNickname(String name) {
        final UsersEntity entity = repository.findByNickname(name);
        return !ObjectUtils.isEmpty(entity);
    }

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
        final String newUsername = userRequest.getUsername();
        if (newUsername.equalsIgnoreCase("admin")) {
            return;
        }
        final UsersEntity entity = findById(userRequest.getId());
        final String newNickname = userRequest.getNickname();
        final String oldNickname = entity.getNickname();
        if (!oldNickname.equalsIgnoreCase(newNickname)) {
            if (checkNickname(newNickname)) {
                throw new BadRequestException(newNickname + " 已存在");
            }
        }

        final String oldUsername = entity.getUsername();
        if (!oldUsername.equalsIgnoreCase(newUsername)) {
            if (checkUsername(newUsername)) {
                throw new BadRequestException(newUsername + " 已存在");
            }
        }

        BeanUtils.copyProperties(userRequest, entity);
        entity.setUpdateTime(new Date());
        repository.save(entity);
    }

    public void create(UserCreateRequest userRequest) {
        final String newNickname = userRequest.getNickname();
        final String newUsername = userRequest.getUsername();

        if (checkNickname(newNickname)) {
            throw new BadRequestException(newNickname + " 已存在");
        }

        if (checkUsername(newUsername)) {
            throw new BadRequestException(newUsername + " 已存在");
        }

        final UsersEntity entity = userRequest.toEntity();
        entity.setPassword("123456");
        repository.save(entity);
    }
}
