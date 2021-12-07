package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.area.AreaGetPageDto;
import com.langxy.house.dto.area.AreaInsertDto;
import com.langxy.house.dto.area.AreaModifyDto;
import com.langxy.house.pojo.Area;

import java.util.List;
import java.util.Map;

public interface IAreaService {

    void insert(AreaInsertDto dto);

    void modify(AreaModifyDto dto);

    List<Map<String, Object>> getAll();

    Area getById(Long id);

    Page<Map<String, Object>> getPage(AreaGetPageDto dto);
}
