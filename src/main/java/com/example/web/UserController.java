package com.example.web;

import com.example.dao.TokenEntity;
import com.example.dao.UsersEntity;
import com.example.service.TokenService;
import com.example.service.UsersService;
import com.example.tools.StrTool;
import com.example.web.request.UserCreateRequest;
import com.example.web.request.UserLoginRequest;
import com.example.web.request.UserUpdateRequest;
import com.example.web.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController extends AbstractBaseController {


    @Autowired
    UsersService usersService;
    @Autowired
    TokenService tokenService;


    @GetMapping
    public Object selectAll() {
        List<UsersEntity> entities = usersService.finAll();
        return responseOK(entities);
    }


    @GetMapping(value = "/{id}")
    public Object selectById(@PathVariable Integer id) {
        final UsersEntity entity = usersService.findById(id);
        return responseOK(entity);
    }


    @DeleteMapping(value = "/{id}")
    public Object deleteById(@PathVariable Integer id) {
        usersService.deleteById(id);
        return responseOK();
    }


    @PostMapping
    public Object createEntity(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        usersService.create(userCreateRequest);
        return responseOK();
    }


    @PutMapping
    public Object updateEntity(@Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        usersService.update(userUpdateRequest);
        return responseOK();
    }


    @PostMapping(value = "/change-password")
    public Object changePassword(@RequestBody String password, HttpServletRequest request) {

        final HttpSession session = request.getSession();
        final String username = (String) session.getAttribute("username");
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
        Boolean admin = (Boolean) session.getAttribute("admin");
        Boolean edit = (Boolean) session.getAttribute("edit");
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setNickname(nickname);
        userInfo.setUsername(username);
        userInfo.setAdmin(admin);
        userInfo.setEdit(edit);
        return responseOK(userInfo);
    }

    @PostMapping(value = "/login")
    public Object login(@Validated @RequestBody UserLoginRequest userRequest, HttpServletRequest request) {

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
        session.setAttribute("admin", usersEntity.getAdmin());
        session.setAttribute("edit", usersEntity.getEdit());
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
