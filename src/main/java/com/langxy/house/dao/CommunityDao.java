package com.langxy.house.dao;

import com.langxy.house.pojo.Community;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommunityDao {

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateName") Long updateName);

    int insert(Community record);

    Community selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Community record);

    List<Map<String, Object>> selectAll();

    List<Map<String, Object>> selectByStreetId(Long streetId);

    Community getInfoByStreetIdAndCommunityName(@Param("streetId") Long streetId, @Param("communityName") String communityName);

}