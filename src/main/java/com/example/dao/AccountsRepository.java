package com.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountsRepository extends BaseRepository<AccountsEntity> {

    AccountsEntity findByItemName(String itemName);

    @Query(value = "SELECT IFNULL(sum(item_price),0) FROM `accounts`  where YEAR(create_time) = (SELECT DATE_FORMAT(NOW(), '%Y')) ", nativeQuery = true)
    int incomeSum();

    @Query(value = "select MONTH(create_time) as month ,IFNULL(sum(item_price) ,0) as price from accounts where YEAR(create_time) = (SELECT DATE_FORMAT(NOW(), '%Y')) GROUP BY MONTH(create_time)", nativeQuery = true)
    List<Map<String, Object>> incomeMonth();

}
