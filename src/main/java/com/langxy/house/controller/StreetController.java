package com.langxy.house.controller;

import com.langxy.house.annotation.LoginToken;
import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.street.StreetGetPageDto;
import com.langxy.house.dto.street.StreetInsertDto;
import com.langxy.house.dto.street.StreetModifyDto;
import com.langxy.house.jwt.JwtService;
import com.langxy.house.service.IStreetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "street")
public class StreetController {

    @Resource
    private IStreetService service;

    @Resource
    private JwtService jwtService;

    @LoginToken
    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, @RequestBody StreetInsertDto dto) {
        dto.setCreateUser(jwtService.getUserId(request));
        service.insert(dto);
        return HttpResp.success();
    }

    @LoginToken
    @PutMapping
    public HttpResp<Object> modify(HttpServletRequest request, @RequestBody StreetModifyDto dto) {
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
    public HttpResp<Object> getPage(StreetGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

    @LoginToken
    @GetMapping(value = "getByAreaId/{areaId}")
    public HttpResp<Object> getByAreaId(@PathVariable Long areaId) {
        List<Map<String, Object>> data = service.getByAreaId(areaId);
        return HttpResp.successData(data);
    }

}
