package com.langxy.house.controller;

import com.langxy.house.annotation.LoginToken;
import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.community.CommunityGetPageDto;
import com.langxy.house.dto.community.CommunityInsertDto;
import com.langxy.house.dto.community.CommunityModifyDto;
import com.langxy.house.jwt.JwtService;
import com.langxy.house.service.ICommunityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "community")
public class CommunityController {

    @Resource
    private ICommunityService service;

    @Resource
    private JwtService jwtService;

    @LoginToken
    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, @RequestBody CommunityInsertDto dto) {
        dto.setCreateUser(jwtService.getUserId(request));
        service.insert(dto);
        return HttpResp.success();
    }

    @LoginToken
    @PutMapping
    public HttpResp<Object> modify(HttpServletRequest request, @RequestBody CommunityModifyDto dto) {
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
    public HttpResp<Object> getPage(CommunityGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

    @LoginToken
    @GetMapping(value = "getByStreetId/{streetId}")
    public HttpResp<Object> getByStreetId(@PathVariable Long streetId) {
        List<Map<String, Object>> data = service.getByStreetId(streetId);
        return HttpResp.successData(data);
    }

}
