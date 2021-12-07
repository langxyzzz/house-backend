package com.langxy.house.dao;

import com.langxy.house.pojo.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HouseDao {

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateName") Long updateName);

    int insert(House record);

    House selectByPrimaryKey(Long id);

    int updateByPrimaryKey(House record);

    List<Map<String, Object>> selectAll();

    Map<String, Object> selectById(Long id);

    Map<String, Object> selectNotHomeownerById(Long id);
}