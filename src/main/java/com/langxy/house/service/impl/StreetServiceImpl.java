package com.langxy.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langxy.house.commons.Page;
import com.langxy.house.dao.StreetDao;
import com.langxy.house.dto.street.StreetGetPageDto;
import com.langxy.house.dto.street.StreetInsertDto;
import com.langxy.house.dto.street.StreetModifyDto;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.Area;
import com.langxy.house.pojo.Street;
import com.langxy.house.service.IAreaService;
import com.langxy.house.service.IStreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StreetServiceImpl implements IStreetService {

    @Resource
    private StreetDao dao;

    @Resource
    private IAreaService service;

    @Override
    public void insert(StreetInsertDto dto) {
        Area area = service.getById(dto.getAreaId());
        if (area == null) {
            throw new CustomerException(500, "this Id area is not found");
        }
        Street street = new Street();
        Street dbStreet = dao.getInfoByAreaIdAndStreetName(dto.getAreaId(), dto.getStreetName());
        if (dbStreet != null) {
            throw new CustomerException(500, "当前社区已存在");
        }
        street.setAreaId(dto.getAreaId());
        street.setStreetName(dto.getStreetName());
        street.setCreateUser(dto.getCreateUser());
        int rowCount = dao.insert(street);
        if (rowCount == 0) {
            throw new CustomerException(500, "insert failure");
        }
    }

    @Override
    public void modify(StreetModifyDto dto) {
        Area area = service.getById(dto.getAreaId());
        if (area == null) {
            throw new CustomerException(500, "this Id area is not found");
        }
        Street street = dao.selectByPrimaryKey(dto.getId());
        if (street == null) {
            throw new CustomerException(500, "this Id street is not found");
        }
        Street dbStreet = dao.getInfoByAreaIdAndStreetName(dto.getAreaId(), dto.getStreetName());
        if (dbStreet != null) {
            throw new CustomerException(500, "当前社区已存在");
        }
        street.setAreaId(dto.getAreaId());
        street.setStreetName(dto.getStreetName());
        street.setUpdateUser(dto.getUpdateUser());
        int rowCount = dao.updateByPrimaryKey(street);
        if (rowCount == 0) {
            throw new CustomerException(500, "modify failure");
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return dao.selectAll();
    }

    @Override
    public Page<Map<String, Object>> getPage(StreetGetPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Map<String, Object>> data = dao.selectAll();
        return Page.generatePage(new PageInfo<>(data));
    }

    @Override
    public List<Map<String, Object>> getByAreaId(Long areaId) {
        // todo areaId 不能为空
        Area area = service.getById(areaId);
        if (area == null) {
            throw new CustomerException(500, "this Id area is not found");
        }
        return dao.selectByAreaId(areaId);
    }

    @Override
    public Street getById(Long id) {
        return dao.selectByPrimaryKey(id);
    }
}
