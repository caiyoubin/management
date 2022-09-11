package com.example.service;

import com.example.dao.BaseEntity;
import com.example.dao.BaseRepository;
import com.example.web.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class BaseService<T extends BaseEntity> {

    @Autowired
    BaseRepository<T> repository;

    public T findById(Integer id) {
        final Optional<T> optional = repository.findById(id);
        final boolean empty = optional.isEmpty();
        if (empty) {
            throw  new BadRequestException("id " + id + " does not exist");
        }
        return optional.get();
    }

}
