package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.community.CommunityGetPageDto;
import com.langxy.house.dto.community.CommunityInsertDto;
import com.langxy.house.dto.community.CommunityModifyDto;
import com.langxy.house.pojo.Community;

import java.util.List;
import java.util.Map;

public interface ICommunityService {

    void insert(CommunityInsertDto dto);

    void modify(CommunityModifyDto dto);

    List<Map<String, Object>> getAll();

    List<Map<String, Object>> getByStreetId(Long streetId);

    Page<Map<String, Object>> getPage(CommunityGetPageDto dto);

    Community getById(Long id);
}
