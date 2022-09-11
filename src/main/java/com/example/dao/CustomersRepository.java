package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomersEntity, Integer> {
}
