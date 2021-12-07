package com.langxy.house.controller;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.user.UserGetPageDto;
import com.langxy.house.dto.user.UserInsertDto;
import com.langxy.house.dto.user.UserModifyDto;
import com.langxy.house.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Resource
    private IUserService service;

    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, UserInsertDto dto) {
        service.insert(dto);
        return HttpResp.success();
    }

    @PutMapping
    public HttpResp<Object> modify(HttpServletRequest request, UserModifyDto dto) {
        service.modify(dto);
        return HttpResp.success();
    }

    @GetMapping(value = "getAll")
    public HttpResp<Object> getAll() {
        List<Map<String, Object>> data = service.getAll();
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getPage")
    public HttpResp<Object> getPage(UserGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

}
