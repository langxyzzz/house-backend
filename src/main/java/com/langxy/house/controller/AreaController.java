package com.langxy.house.controller;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.commons.Page;
import com.langxy.house.dto.area.AreaGetPageDto;
import com.langxy.house.dto.area.AreaInsertDto;
import com.langxy.house.dto.area.AreaModifyDto;
import com.langxy.house.service.IAreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "area")
public class AreaController {

    @Resource
    private IAreaService service;

    @PostMapping
    public HttpResp<Object> insert(@RequestBody AreaInsertDto dto) {
        service.insert(dto);
        return HttpResp.success();
    }

    @PutMapping
    public HttpResp<Object> modify(@RequestBody AreaModifyDto dto) {
        service.modify(dto);
        return HttpResp.success();
    }

    @GetMapping(value = "getAll")
    public HttpResp<Object> getAll() {
        List<Map<String, Object>> data = service.getAll();
        return HttpResp.successData(data);
    }

    @GetMapping(value = "getPage")
    public HttpResp<Object> getPage(AreaGetPageDto dto) {
        Page<Map<String, Object>> data = service.getPage(dto);
        return HttpResp.successData(data);
    }

}
