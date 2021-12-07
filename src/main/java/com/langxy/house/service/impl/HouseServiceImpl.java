package com.langxy.house.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langxy.house.commons.Constants;
import com.langxy.house.commons.Page;
import com.langxy.house.dao.HouseDao;
import com.langxy.house.dto.house.HouseGetPageDto;
import com.langxy.house.dto.house.HouseInsertDto;
import com.langxy.house.dto.house.HouseUpdateDto;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.*;
import com.langxy.house.service.*;
import com.langxy.house.util.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HouseServiceImpl implements IHouseService {

    @Resource
    private HouseDao dao;

    @Resource
    private IAreaService areaService;

    @Resource
    private IStreetService streetService;

    @Resource
    private ICommunityService communityService;

    @Resource
    private IResService resService;

    @Override
    public void insert(HouseInsertDto dto) {
        House house = JsonUtils.string2obj(JsonUtils.obj2string(dto), new TypeReference<House>() {
        });
        checkId(dto.getAreaId(), dto.getStreetId(), dto.getStreetId());
        int rowCount = dao.insert(house);
        if (rowCount == 0) {
            throw new CustomerException(500, "insert failure");
        }
        List<Long> resIdList = Stream.of(dto.getResIds().split(",")).map(Long::valueOf).collect(Collectors.toList());
        List<Res> resList = new ArrayList<>();
        resIdList.forEach(item -> {
            Res res = new Res();
            res.setId(item);
            res.setHouseId(house.getId());
            res.setIsDelete(0);
            res.setUpdateUser(dto.getCreateUser());
            resList.add(res);
        });
        rowCount = resService.updateHouseId(resList);
        if (rowCount == 0) {
            throw new CustomerException(500, "update failure");
        }
    }

    @Override
    public void update(HouseUpdateDto dto) {
        House house = JsonUtils.string2obj(JsonUtils.obj2string(dto), new TypeReference<House>() {
        });
        checkId(dto.getAreaId(), dto.getStreetId(), dto.getCommunityId());
        int rowCount = dao.updateByPrimaryKey(house);
        if (rowCount == 0) {
            throw new CustomerException(500, "update failure");
        }
        List<Res> resList = new ArrayList<>();
        List<Long> newIdList = Stream.of(dto.getResIds().split(",")).map(Long::valueOf).collect(Collectors.toList());
        List<Long> resIdList = resService.getIdByHouseId(dto.getId());
        List<Long> delIdList = resIdList.stream().filter(item -> !newIdList.contains(item)).collect(Collectors.toList());
        newIdList.forEach(item -> {
            Res res = new Res();
            res.setId(item);
            res.setHouseId(dto.getId());
            res.setIsDelete(0);
            res.setUpdateUser(dto.getUpdateUser());
            resList.add(res);
        });
        delIdList.forEach(item -> {
            Res res = new Res();
            res.setId(item);
            res.setIsDelete(1);
            res.setUpdateUser(dto.getUpdateUser());
        });
        if (resList.size() > 0) {
            rowCount = resService.updateHouseId(resList);
            if (rowCount == 0) {
                throw new CustomerException(500, "update failure");
            }
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return dao.selectAll();
    }

    @Override
    public Page<Map<String, Object>> getPage(HouseGetPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Map<String, Object>> data = dao.selectAll();
        return Page.generatePage(new PageInfo<>(data));
    }

    @Override
    public Map<String, Object> getById(Long id) {
        Map<String, Object> data = dao.selectById(id);
        if (data == null) {
            throw new CustomerException(500, "this Id house is not found");
        }
        List<Res> pathList = resService.getByHouseId(id);
        List<Map<String, Object>> imgList = new ArrayList<>();
        pathList.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("path", Constants.REMOTE_PATH + item.getFilename());
            imgList.add(map);
        });
        data.put("imgList", imgList);
        return data;
    }

    @Override
    public Map<String, Object> selectNotHomeownerById(Long id) {
        Map<String, Object> data = dao.selectNotHomeownerById(id);
        if (data == null) {
            throw new CustomerException(500, "this Id house is not found");
        }
        List<Res> pathList = resService.getByHouseId(id);
        List<Map<String, Object>> imgList = new ArrayList<>();
        pathList.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("path", Constants.REMOTE_PATH + item.getFilename());
            imgList.add(map);
        });
        data.put("imgList", imgList);
        return data;
    }

    void checkId (Long areaId, Long streetId, Long communityId) {
        Area area = areaService.getById(areaId);
        if (area == null) {
            throw new CustomerException(500, "this Id area is not found");
        }
        Street street = streetService.getById(streetId);
        if (street == null) {
            throw new CustomerException(500, "this Id street is not found");
        }
        if (!Objects.equals(street.getAreaId(), areaId)) {
            throw new CustomerException(500, "this areaId and streetId do not match");
        }
        Community community = communityService.getById(communityId);
        if (community == null) {
            throw new CustomerException(500, "this Id community is not found");
        }
        if (!Objects.equals(community.getStreetId(), streetId)) {
            throw new CustomerException(500, "this streetId and community do not match");
        }
    }

}
