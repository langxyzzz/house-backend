package com.langxy.house.controller;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.street.StreetGetPageDto;
import com.langxy.house.dto.street.StreetInsertDto;
import com.langxy.house.dto.street.StreetModifyDto;
import com.langxy.house.service.IStreetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "street")
public class StreetController {

    @Resource
    private IStreetService service;

    @PostMapping
    public HttpResp<Object> insert(@RequestBody StreetInsertDto dto) {
        service.insert(dto);
        return HttpResp.success();
    }

    @PutMapping
    public HttpResp<Object> modify(@RequestBody StreetModifyDto dto) {
        service.modify(dto);
        return HttpResp.success();
    }

    @GetMapping(value = "getAll")
    public HttpResp<Object> getAll() {
        List<Map<String, Object>> data = service.getAll();
        return HttpResp.successData(data);
    }

    public HttpResp<Object> getPage(StreetGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getByAreaId/{areaId}")
    public HttpResp<Object> getByAreaId(@PathVariable Long areaId) {
        List<Map<String, Object>> data = service.getByAreaId(areaId);
        return HttpResp.successData(data);
    }

}
