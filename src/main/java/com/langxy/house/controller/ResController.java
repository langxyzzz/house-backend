package com.langxy.house.controller;

import com.langxy.house.annotation.LoginToken;
import com.langxy.house.commons.HttpResp;
import com.langxy.house.jwt.JwtService;
import com.langxy.house.service.IResService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "res")
public class ResController {

    @Resource
    private IResService service;

    @Resource
    private JwtService jwtService;

    @LoginToken
    @PostMapping
    public HttpResp<Object> insert(HttpServletRequest request, @RequestParam("file") List<MultipartFile> multipartFileList) {
        long createUser = jwtService.getUserId(request);
        List<Map<String, Object>> data = service.insert(multipartFileList, createUser);
        return HttpResp.successData(data);
    }

}
