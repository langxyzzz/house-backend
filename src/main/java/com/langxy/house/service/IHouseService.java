package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.house.HouseGetPageDto;
import com.langxy.house.dto.house.HouseInsertDto;
import com.langxy.house.dto.house.HouseUpdateDto;

import java.util.List;
import java.util.Map;

public interface IHouseService {

    void insert(HouseInsertDto dto);

    void update(HouseUpdateDto dto);

    List<Map<String, Object>> getAll();

    Page<Map<String, Object>> getPage(HouseGetPageDto dto);

    Map<String, Object> getById(Long id);

    Map<String, Object> selectNotHomeownerById(Long id);
}
