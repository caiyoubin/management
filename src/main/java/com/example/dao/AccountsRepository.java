package com.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountsRepository extends BaseRepository<AccountsEntity> {

    AccountsEntity findByItemName(String itemName);

    // todo 不能用create_time 得用项目的接单时间
    @Query(value = "SELECT IFNULL(sum(item_price),0) FROM `accounts`  where YEAR(create_time) = (SELECT DATE_FORMAT(NOW(), '%Y')) ", nativeQuery = true)
    int incomeSum();

    // todo 同上  给这张表加个接单时间 然后每次都同步一下接单时间 （以最早的接单时间为准）
    @Query(value = "select MONTH(create_time) as month ,IFNULL(sum(item_price) ,0) as price from accounts where YEAR(create_time) = (SELECT DATE_FORMAT(NOW(), '%Y')) GROUP BY MONTH(create_time)", nativeQuery = true)
    List<Map<String, Object>> incomeMonth();

    void deleteByItemName(String itemName);
}
