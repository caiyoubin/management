package com.example.service;

import com.example.dao.BaseEntity;
import com.example.dao.BaseRepository;
import com.example.web.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class BaseService<T extends BaseEntity> {

    @Autowired
    BaseRepository<T> baseRepository;

    public T findById(Integer id) {
        final Optional<T> optional = baseRepository.findById(id);
        final boolean empty = optional.isEmpty();
        if (empty) {
            throw  new BadRequestException("id " + id + " 不存在");
        }
        return optional.get();
    }

}
