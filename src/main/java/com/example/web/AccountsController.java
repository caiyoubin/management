package com.example.web;

import com.example.dao.AccountsEntity;
import com.example.service.AccountsService;
import com.example.web.request.AccountsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountsController extends AbstractBaseController {

    @Autowired
    AccountsService accountsService;


    @GetMapping
    public Object selectAllAccounts() {
       List<AccountsEntity>  entities = accountsService.finAll();
        return responseOK(entities);
    }


    @GetMapping(value = "/{id}")
    public Object selectById(@PathVariable Integer id) {
        final AccountsEntity entity = accountsService.findById(id);
        return responseOK(entity);
    }


    @DeleteMapping(value = "/{id}")
    public Object deleteById(@PathVariable Integer id) {
        accountsService.deleteById(id);
        return responseOK();
    }

    @PostMapping
    public Object createAccount(@Validated @RequestBody AccountsRequest accountsRequest,  HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String nickname = (String)session.getAttribute("nickname");
        accountsRequest.setNickname(nickname);
        accountsService.save(accountsRequest);
        return responseOK();
    }


    @PutMapping
    public Object updateAccounts(@Validated @RequestBody AccountsRequest accountsRequest, HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String nickname = (String)session.getAttribute("nickname");
        accountsRequest.setNickname(nickname);
        accountsService.update(accountsRequest);
        return responseOK();
    }




}
