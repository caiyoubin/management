package com.example.web;

import com.example.dao.BusinessEntity;
import com.example.dao.CustomersEntity;
import com.example.service.AccountsService;
import com.example.service.BusinessService;
import com.example.service.CustomersService;
import com.example.web.request.BusinessCreateRequest;
import com.example.web.request.BusinessUpdateRequest;
import com.example.web.vo.BusinessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/business")
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
            final CustomersEntity customer = customersService.findById(entity.getCustomerId());
            vo.CopyEntity(entity, customer.getName());
            res.add(vo);
        }
        return responseOK(res);
    }

    @GetMapping(value = "/{id}")
    public Object selectById(@PathVariable Integer id) {
        final BusinessEntity entity = businessService.findById(id);
        final CustomersEntity customer = customersService.findById(entity.getCustomerId());
        final BusinessVo vo = new BusinessVo();
        vo.CopyEntity(entity, customer.getName());
        return responseOK(vo);
    }

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

}
