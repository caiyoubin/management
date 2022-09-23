package com.example.web;

import com.example.dao.TokenEntity;
import com.example.dao.UsersEntity;
import com.example.service.TokenService;
import com.example.service.UsersService;
import com.example.tools.StrTool;
import com.example.web.exception.BadRequestException;
import com.example.web.request.UserRequest;
import com.example.web.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController extends AbstractBaseController {


    @Autowired
    UsersService usersService;
    @Autowired
    TokenService tokenService;


    @PostMapping(value = "/change-password")
    public Object changePassword(@RequestBody String password, HttpServletRequest request) {

        final HttpSession session = request.getSession();
        final String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new BadRequestException("Please login and try again.");
        }

        UsersEntity entity = usersService.findByUsername(username);
        entity.setPassword(password);
        usersService.save(entity);
        return responseOK();
    }


    @GetMapping(value = "/userinfo")
    public Object username(HttpServletRequest request) {

        final HttpSession session = request.getSession();
        final String nickname = (String) session.getAttribute("nickname");
        final String username = (String) session.getAttribute("username");
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setNickname(nickname);
        userInfo.setUsername(username);
        return responseOK(userInfo);
    }

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
        session.setAttribute("nickname", usersEntity.getNickname());
        final TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setUsername(username);

        tokenService.save(tokenEntity);
        return responseOK();
    }


    @GetMapping(value = "/logout")
    public Object logout(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.setAttribute("token", null);
        return responseOK();
    }


}
