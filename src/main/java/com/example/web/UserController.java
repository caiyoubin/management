package com.example.web;

import com.example.dao.TokenEntity;
import com.example.dao.UsersEntity;
import com.example.service.TokenService;
import com.example.service.UsersService;
import com.example.tools.StrTool;
import com.example.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/users")
public class UserController extends AbstractBaseController {


    @Autowired
    UsersService usersService;
    @Autowired
    TokenService tokenService;


    @PostMapping(value = "/login")
    public Object login(@Validated @RequestBody UserRequest userRequest, HttpServletRequest request) {

        final String username = userRequest.getUsername();
        final String password = userRequest.getPassword();

        UsersEntity usersEntity = usersService.findByUsernameAndPassword(username, password);
        if (ObjectUtils.isEmpty(usersEntity)) {
            return responseError("The account or password is incorrect");
        }
        final HttpSession session = request.getSession();
        final String token = StrTool.getMD5(password);
        session.setAttribute("token", token);
        session.setAttribute("username", username);
        final TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setUsername(username);

        tokenService.save(tokenEntity);
        return responseOK();
    }


}
