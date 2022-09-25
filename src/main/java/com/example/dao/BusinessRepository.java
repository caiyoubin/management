package com.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends BaseRepository<BusinessEntity> {

    @Query(value = "select count(1) from (SELECT count(1) FROM `business` where  YEAR(create_time) = (SELECT DATE_FORMAT(NOW(), '%Y'))  group by item_name ) t ", nativeQuery = true)
    int orderCount();

    @Query(value = "select sum(1) from (select count(1) from business where YEAR(create_time) = ?1 and Month(create_time)=?2 GROUP BY customer_name) t", nativeQuery = true)
    int currentMonthCustomers(int year, int month);
}
