package com.langxy.house.controller;

import com.langxy.house.annotation.LoginToken;
import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.area.AreaGetPageDto;
import com.langxy.house.dto.area.AreaInsertDto;
import com.langxy.house.dto.area.AreaModifyDto;
import com.langxy.house.jwt.JwtService;
import com.langxy.house.service.IAreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "area")
public class AreaController {

    @Resource
    private IAreaService service;

    @Resource
    private JwtService jwtService;

    @LoginToken
    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, @RequestBody AreaInsertDto dto) {
        dto.setCreateUser(jwtService.getUserId(request));
        service.insert(dto);
        return HttpResp.success();
    }

    @LoginToken
    @PutMapping
    public HttpResp<Object> modify(HttpServletRequest request, @RequestBody AreaModifyDto dto) {
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
    public HttpResp<Object> getPage(AreaGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

}
