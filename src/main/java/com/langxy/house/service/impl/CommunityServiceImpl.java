package com.langxy.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langxy.house.commons.Page;
import com.langxy.house.dao.CommunityDao;
import com.langxy.house.dto.community.CommunityGetPageDto;
import com.langxy.house.dto.community.CommunityInsertDto;
import com.langxy.house.dto.community.CommunityModifyDto;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.Community;
import com.langxy.house.pojo.Street;
import com.langxy.house.service.ICommunityService;
import com.langxy.house.service.IStreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 小区 service 接口实现
 *
 * @author langxy
 */
@Service
public class CommunityServiceImpl implements ICommunityService {

    @Resource
    private CommunityDao dao;

    @Resource
    private IStreetService streetService;

    @Override
    public void insert(CommunityInsertDto dto) {
        Community community = new Community();
        community.setStreetId(dto.getStreetId());
        community.setCommunityName(dto.getCommunityName());
        community.setCreateUser(dto.getCreateUser());
        int rowCount = dao.insert(community);
        if (rowCount == 0) {
            throw new CustomerException(500, "insert failure");
        }
    }

    @Override
    public void modify(CommunityModifyDto dto) {
        Street street = streetService.getById(dto.getStreetId());
        if (street == null) {
            throw new CustomerException(500, "this Id street is not found");
        }
        Community community = dao.selectByPrimaryKey(dto.getId());
        if (community == null) {
            throw new CustomerException(500, "this Id community is not found");
        }
        community.setStreetId(dto.getStreetId());
        community.setCommunityName(dto.getCommunityName());
        community.setUpdateUser(dto.getUpdateUser());
        int rowCount = dao.updateByPrimaryKey(community);
        if (rowCount == 0) {
            throw new CustomerException(500, "modify failure");
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return dao.selectAll();
    }

    @Override
    public List<Map<String, Object>> getByStreetId(Long streetId) {
        Street street = streetService.getById(streetId);
        if (street == null) {
            throw new CustomerException(500, "this Id street is not found");
        }
        return dao.selectByStreetId(street.getId());
    }

    @Override
    public Page<Map<String, Object>> getPage(CommunityGetPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Map<String, Object>> data = dao.selectAll();
        return Page.generatePage(new PageInfo<>(data));
    }

    @Override
    public Community getById(Long id) {
        return dao.selectByPrimaryKey(id);
    }
}
