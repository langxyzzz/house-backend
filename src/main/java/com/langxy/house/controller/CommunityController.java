package com.langxy.house.controller;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.community.CommunityGetPageDto;
import com.langxy.house.dto.community.CommunityInsertDto;
import com.langxy.house.dto.community.CommunityModifyDto;
import com.langxy.house.service.ICommunityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CommunityController {

    @Resource
    private ICommunityService service;

    @PostMapping
    public HttpResp<Object> insert(@RequestBody CommunityInsertDto dto) {
        service.insert(dto);
        return HttpResp.success();
    }

    @PutMapping
    public HttpResp<Object> modify(@RequestBody CommunityModifyDto dto) {
        service.modify(dto);
        return HttpResp.success();
    }

    @GetMapping(value = "getAll")
    public HttpResp<Object> getAll() {
        List<Map<String, Object>> data = service.getAll();
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getPage")
    public HttpResp<Object> getPage(CommunityGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getByStreetId/{streetId}")
    public HttpResp<Object> getByStreetId(@PathVariable Long streetId) {
        List<Map<String, Object>> data = service.getByStreetId(streetId);
        return HttpResp.successData(data);
    }

}
