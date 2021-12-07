package com.langxy.house.controller;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.house.HouseGetPageDto;
import com.langxy.house.dto.house.HouseInsertDto;
import com.langxy.house.dto.house.HouseUpdateDto;
import com.langxy.house.pojo.House;
import com.langxy.house.service.IHouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class HouseController {

    @Resource
    private IHouseService service;

    @PostMapping
    public HttpResp<Object> insert(HouseInsertDto dto) {
        service.insert(dto);
        return HttpResp.success();
    }

    @PutMapping
    public HttpResp<Object> update(HouseUpdateDto dto) {
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
