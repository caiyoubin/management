package com.example.web;

import com.example.dao.BusinessEntity;
import com.example.service.BusinessService;
import com.example.web.request.BusinessCreateRequest;
import com.example.web.request.BusinessUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusinessController extends AbstractBaseController {

    @Autowired
    BusinessService businessService;

    @PostMapping(value = "/business")
    public Object createBusiness(@Validated @RequestBody BusinessCreateRequest request) {
        final BusinessEntity entity = request.toEntity();
        businessService.save(entity);
        return responseOK();
    }


    @PutMapping(value = "/business")
    public Object updateBusiness(@Validated @RequestBody BusinessUpdateRequest request) {
        businessService.update(request);
        return responseOK();
    }


    @GetMapping(value = "/business/search/{search}")
    public Object searchBusiness(@PathVariable String search) {
        // todo
        return responseOK();
    }

}
