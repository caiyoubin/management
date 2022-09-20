package com.example.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends BaseRepository<CustomersEntity> {

    CustomersEntity findByCustomerName(String customerName);

}
