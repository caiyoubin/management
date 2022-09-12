package com.example.web;

import com.example.dao.BusinessEntity;
import com.example.service.AccountsService;
import com.example.service.BusinessService;
import com.example.web.request.BusinessCreateRequest;
import com.example.web.request.BusinessUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/business")
public class BusinessController extends AbstractBaseController {

    @Autowired
    BusinessService businessService;
    @Autowired
    AccountsService accountsService;


    @Transactional
    @PostMapping
    public Object createBusiness(@Validated @RequestBody BusinessCreateRequest request) {
        final BusinessEntity entity = request.toEntity();
        businessService.save(entity);
        final Integer itemId = entity.getItemId();
        accountsService.saveByItemId(itemId);
        return responseOK();
    }


    @PutMapping
    public Object updateBusiness(@Validated @RequestBody BusinessUpdateRequest request) {
        businessService.update(request);
        return responseOK();
    }


    @GetMapping(value = "/search/{search}")
    public Object searchBusiness(@PathVariable String search) {
        // todo
        return responseOK();
    }

}
