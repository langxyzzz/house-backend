package com.langxy.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langxy.house.commons.Page;
import com.langxy.house.dao.AreaDao;
import com.langxy.house.dto.area.AreaGetPageDto;
import com.langxy.house.dto.area.AreaInsertDto;
import com.langxy.house.dto.area.AreaModifyDto;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.Area;
import com.langxy.house.service.IAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements IAreaService {

    @Resource
    private AreaDao dao;

    @Override
    public void insert(AreaInsertDto dto) {
        Area area = new Area();
        area.setAreaName(dto.getAreaName());
        area.setCreateUser(dto.getCreateUser());
        int rowCount = dao.insert(area);
        if (rowCount == 0) {
            throw new CustomerException(500 , "insert failure");
        }
    }

    @Override
    public void modify(AreaModifyDto dto) {
        Area area = dao.selectByPrimaryKey(dto.getId());
        if (area == null) {
            throw new CustomerException(500, "this Id area is not found");
        }
        area.setAreaName(dto.getAreaName());
        area.setUpdateUser(dto.getUpdateUser());
        int rowCount = dao.updateByPrimaryKey(area);
        if (rowCount == 0) {
            throw new CustomerException(500 , "modify failure");
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return dao.selectAll();
    }

    @Override
    public Area getById(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public Page<Map<String, Object>> getPage(AreaGetPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Map<String, Object>> data = dao.selectAll();
        return Page.generatePage(new PageInfo<>(data));
    }

}
