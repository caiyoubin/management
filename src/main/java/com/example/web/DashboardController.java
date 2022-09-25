package com.example.web;

import com.example.service.AccountsService;
import com.example.service.BusinessService;
import com.example.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/dashboard")
public class DashboardController extends AbstractBaseController {

    @Autowired
    BusinessService businessService;
    @Autowired
    AccountsService accountsService;
    @Autowired
    CustomersService customersService;

    @GetMapping("/customers/sum")
    public Object countCustomers() {
        int sum = customersService.countCustomers();
        Map<String, Integer> map = new HashMap<>();
        map.put("customers", sum);
        return responseOK(map);
    }

    @GetMapping("/customers/current-month")
    public Object currentMonth() {
        int sum = customersService.countCustomers();
        int current =  businessService.currentMonthCustomers();
        Map<String, Integer> map = new HashMap<>();
        map.put("customers", sum-current);
        map.put("current", current);
        return responseOK(map);
    }

    @GetMapping("/business/order-count")
    public Object orderCount() {
        int sum = businessService.orderCount();
        Map<String, Integer> map = new HashMap<>();
        map.put("orderCount", sum);
        return responseOK(map);
    }

    @GetMapping("/accounts/income/sum")
    public Object incomeSum() {
        int sum = accountsService.incomeSum();
        Map<String, Integer> map = new HashMap<>();
        map.put("incomeSum", sum);
        return responseOK(map);
    }

    @GetMapping("/accounts/income/month")
    public Object incomeMonth() {
        Double[] doubles = accountsService.incomeMonth();
        return responseOK(doubles);
    }
}
