package com.example.web;

import com.example.dao.BusinessEntity;
import com.example.dao.CustomersEntity;
import com.example.service.AccountsService;
import com.example.service.BusinessService;
import com.example.service.CustomersService;
import com.example.web.request.BusinessRequest;
import com.example.web.vo.BusinessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/business")
public class BusinessController extends AbstractBaseController {

    @Autowired
    BusinessService businessService;
    @Autowired
    AccountsService accountsService;
    @Autowired
    CustomersService customersService;


    @GetMapping
    public Object selectAllBusiness() {
        List<BusinessEntity> list = businessService.findByAll();
        List<BusinessVo> res = new ArrayList<>();
        for (BusinessEntity entity : list) {
            final BusinessVo vo = new BusinessVo();
            final CustomersEntity customer = customersService.findByCustomerName(entity.getCustomerName());
            vo.CopyEntity(entity, customer.getCustomerName());
            res.add(vo);
        }
        return responseOK(res);
    }

    @GetMapping(value = "/{id}")
    public Object selectById(@PathVariable Integer id) {
        final BusinessEntity entity = businessService.findById(id);
        final CustomersEntity customer = customersService.findByCustomerName(entity.getCustomerName());
        final BusinessVo vo = new BusinessVo();
        vo.CopyEntity(entity, customer.getCustomerName());
        return responseOK(vo);
    }

    @DeleteMapping(value = "/{id}")
    public Object deleteById(@PathVariable Integer id) {
        businessService.deleteById(id);
        return responseOK();
    }

    @Transactional
    @PostMapping
    public Object createBusiness(@Validated @RequestBody BusinessRequest businessRequest, HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String nickname = (String)session.getAttribute("nickname");
        businessRequest.setNickname(nickname);
        final BusinessEntity entity = businessRequest.toEntity();
        businessService.save(entity);
        final String itemName = entity.getItemName();
        accountsService.saveByItemId(itemName);
        return responseOK();
    }


    @PutMapping
    public Object updateBusiness(@Validated @RequestBody BusinessRequest businessRequest, HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String nickname = (String)session.getAttribute("nickname");
        businessRequest.setNickname(nickname);
        businessService.update(businessRequest);
        return responseOK();
    }

}
