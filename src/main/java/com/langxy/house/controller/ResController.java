package com.langxy.house.controller;

import com.langxy.house.commons.HttpResp;
import com.langxy.house.service.IResService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "res")
public class ResController {

    @Resource
    private IResService service;

    @PostMapping
    public HttpResp<Object> insert(List<MultipartFile> multipartFileList) {
        List<Map<String, Object>> data = service.insert(multipartFileList, 10L);
        return HttpResp.successData(data);
    }

}
