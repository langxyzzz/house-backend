package com.langxy.house.dao;

import com.langxy.house.pojo.Street;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StreetDao {

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateName") Long updateName);

    int insert(Street record);

    Street selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Street record);

    List<Map<String, Object>> selectAll();

    List<Map<String, Object>> selectByAreaId(Long areaId);
}