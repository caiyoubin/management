package com.example.web;

import com.example.dao.UsersEntity;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/users")
public class UserController extends AbstractBaseController {


    @Autowired
    UsersService usersService;


    @PostMapping(value = "/login")
//    public Object login(@Validated @RequestBody UserRequest userRequest, HttpServletRequest request) {
    public Object login(@Param("") String userId, @Param("") String password, HttpServletRequest request) {
        UsersEntity usersEntity = usersService.findByUserIdAndPassword(userId, password);
        if (ObjectUtils.isEmpty(usersEntity)) {
            return responseError("The account or password is incorrect");
        }

        final HttpSession session = request.getSession();
        session.setAttribute("token", userId + password);
        session.setAttribute("userId", userId);
        return responseOK();
    }


}
