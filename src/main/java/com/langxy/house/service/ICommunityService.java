package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.community.CommunityGetPageDto;
import com.langxy.house.dto.community.CommunityInsertDto;
import com.langxy.house.dto.community.CommunityModifyDto;
import com.langxy.house.pojo.Community;

import java.util.List;
import java.util.Map;

/**
 * 小区 service 接口
 *
 * @author langxy
 */
public interface ICommunityService {

    /**
     * 新增小区
     *
     * @param dto
     */
    void insert(CommunityInsertDto dto);

    /**
     * 修改小区
     *
     * @param dto
     */
    void modify(CommunityModifyDto dto);

    /**
     * 获取全部小区信息
     *
     * @return
     */
    List<Map<String, Object>> getAll();

    /**
     * 根据街道 ID 获取小区信息
     *
     * @param streetId
     * @return
     */
    List<Map<String, Object>> getByStreetId(Long streetId);

    /**
     * 获取小区信息列表并分页
     *
     * @param dto
     * @return
     */
    Page<Map<String, Object>> getPage(CommunityGetPageDto dto);

    Community getById(Long id);
}
