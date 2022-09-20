package com.example.dao;

public interface UsersRepository extends BaseRepository<UsersEntity> {
    UsersEntity findByUserIdAndPassword(String userId, String password);
}
