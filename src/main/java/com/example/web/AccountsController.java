package com.example.web;

import com.example.service.AccountsService;
import com.example.web.request.AccountsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountsController extends AbstractBaseController {

    @Autowired
    AccountsService accountsService;


    @PutMapping
    public Object updateAccounts(@Validated @RequestBody AccountsRequest request) {
        accountsService.update(request);
        return responseOK();
    }


}
