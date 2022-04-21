package com.langxy.house.controller;

import com.langxy.house.annotation.LoginToken;
import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.house.HouseGetPageDto;
import com.langxy.house.dto.house.HouseInsertDto;
import com.langxy.house.dto.house.HouseUpdateDto;
import com.langxy.house.jwt.JwtService;
import com.langxy.house.pojo.House;
import com.langxy.house.service.IHouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "house")
public class HouseController {

    @Resource
    private IHouseService service;

    @Resource
    private JwtService jwtService;

    @LoginToken
    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, @RequestBody HouseInsertDto dto) {
        dto.setCreateUser(jwtService.getUserId(request));
        service.insert(dto);
        return HttpResp.success();
    }

    @LoginToken
    @PutMapping
    public HttpResp<Object> update(HttpServletRequest request, @RequestBody HouseUpdateDto dto) {
        dto.setUpdateUser(jwtService.getUserId(request));
        service.update(dto);
        return HttpResp.success();
    }

    @GetMapping(value = "getAll")
    public HttpResp<Object> getAll() {
        List<Map<String, Object>> data = service.getAll();
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getPage")
    public HttpResp<Object> getPage(HouseGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getById/{id}")
    public HttpResp<Object> getById(@PathVariable Long id) {
        Map<String, Object> data = service.getById(id);
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getNotTelById/{id}")
    public HttpResp<Object> getNotTelById(@PathVariable Long id) {
        Map<String, Object> data = service.selectNotHomeownerById(id);
        return HttpResp.successData(data);
    }

}
