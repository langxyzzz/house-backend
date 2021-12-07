package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.user.UserGetPageDto;
import com.langxy.house.dto.user.UserInsertDto;
import com.langxy.house.dto.user.UserModifyDto;

import java.util.List;
import java.util.Map;

public interface IUserService {

    void insert(UserInsertDto dto);

    void modify(UserModifyDto dto);

    List<Map<String, Object>> getAll();

    Page<Map<String, Object>> getPage(UserGetPageDto dto);
}
