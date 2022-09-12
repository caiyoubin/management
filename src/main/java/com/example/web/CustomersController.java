package com.example.web;

import com.example.dao.CustomersEntity;
import com.example.service.CustomersService;
import com.example.web.request.CustomersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customers")
public class CustomersController extends AbstractBaseController {

    @Autowired
    CustomersService customersService;


    @PostMapping
    public Object createCustomer(@Validated @RequestBody CustomersRequest request) {
        final CustomersEntity entity = request.toEntity();
        customersService.save(entity);
        return responseOK();
    }


    @PutMapping
    public Object updateBusiness(@Validated @RequestBody CustomersRequest request) {
        customersService.update(request);
        return responseOK();
    }


}
