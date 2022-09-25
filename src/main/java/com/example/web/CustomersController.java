package com.example.web;

import com.example.dao.CustomersEntity;
import com.example.service.CustomersService;
import com.example.web.request.CustomersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomersController extends AbstractBaseController {

    @Autowired
    CustomersService customersService;


    @GetMapping
    public Object selectAll() {
        List<CustomersEntity> entities = customersService.finAll();
        return responseOK(entities);
    }


    @GetMapping(value = "/{id}")
    public Object selectById(@PathVariable Integer id) {
        final CustomersEntity entity = customersService.findById(id);
        return responseOK(entity);
    }


    @DeleteMapping(value = "/{id}")
    public Object deleteById(@PathVariable Integer id) {
        customersService.deleteById(id);
        return responseOK();
    }


    @PostMapping
    public Object createCustomer(@Validated @RequestBody CustomersRequest customersRequest, HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String nickname = (String)session.getAttribute("nickname");
        final CustomersEntity entity = customersRequest.toEntity();
        entity.setNickname(nickname);
        customersService.save(entity);
        return responseOK();
    }


    @PutMapping
    public Object updateBusiness(@Validated @RequestBody CustomersRequest customersRequest, HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String nickname = (String)session.getAttribute("nickname");
        customersRequest.setNickname(nickname);
        customersService.update(customersRequest);
        return responseOK();
    }


}
