package com.langxy.house.controller;

import com.langxy.house.annotation.LoginToken;
import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.user.UserGetPageDto;
import com.langxy.house.dto.user.UserInsertDto;
import com.langxy.house.dto.user.UserLoginDto;
import com.langxy.house.dto.user.UserModifyDto;
import com.langxy.house.jwt.JwtService;
import com.langxy.house.pojo.User;
import com.langxy.house.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户 controller
 *
 * @author langxy
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Resource
    private IUserService service;

    @Resource
    private JwtService jwtService;

    @PostMapping(value = "login")
    public HttpResp<Object> login(HttpServletResponse response, @RequestBody UserLoginDto dto) {
        final User login = service.login(dto);
        response.setHeader("token", jwtService.getToken(login));
        return HttpResp.success(200, login);
    }

    @LoginToken
    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, @RequestBody UserInsertDto dto) {
        dto.setCreateUser(jwtService.getUserId(request));
        service.insert(dto);
        return HttpResp.success();
    }

    @LoginToken
    @PutMapping
    public HttpResp<Object> modify(HttpServletRequest request, @RequestBody UserModifyDto dto) {
        dto.setUpdateUser(jwtService.getUserId(request));
        service.modify(dto);
        return HttpResp.success();
    }

    @LoginToken
    @GetMapping(value = "getAll")
    public HttpResp<Object> getAll() {
        List<Map<String, Object>> data = service.getAll();
        return HttpResp.successData(data);
    }

    @LoginToken
    @GetMapping(value = "getPage")
    public HttpResp<Object> getPage(UserGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

}
