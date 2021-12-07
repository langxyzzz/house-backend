package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.street.StreetGetPageDto;
import com.langxy.house.dto.street.StreetInsertDto;
import com.langxy.house.dto.street.StreetModifyDto;
import com.langxy.house.pojo.Street;

import java.util.List;
import java.util.Map;

public interface IStreetService {

    void insert(StreetInsertDto dto);

    void modify(StreetModifyDto dto);

    List<Map<String, Object>> getAll();

    Page<Map<String, Object>> getPage(StreetGetPageDto dto);

    List<Map<String, Object>> getByAreaId(Long areaId);

    Street getById(Long id);

}
