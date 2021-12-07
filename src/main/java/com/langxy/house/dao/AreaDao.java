package com.langxy.house.dao;

import com.langxy.house.pojo.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AreaDao {

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateName") Long updateName);

    int insert(Area record);

    Area selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Area record);

    List<Map<String, Object>> selectAll();
}