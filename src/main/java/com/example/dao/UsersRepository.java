package com.example.dao;

public interface UsersRepository extends BaseRepository<UsersEntity> {

    UsersEntity findByUsernameAndPassword(String username, String password);

    UsersEntity findByUsername(String username);

}
