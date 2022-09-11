package com.example.web;

import com.example.dao.BusinessEntity;
import com.example.service.BusinessService;
import com.example.web.request.BusinessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController extends AbstractBaseController {

    @Autowired
    BusinessService businessService;

    @PostMapping(value = "/business")
    public Object business(@Validated @RequestBody BusinessRequest request) {
        final BusinessEntity entity = request.toEntity();
        businessService.save(entity);
        return responseOK();
    }


}
