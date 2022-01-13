package com.langxy.house.dao;

import com.langxy.house.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface
UserDao {

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateName") Long updateName);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKey(User record);

    List<Map<String, Object>> selectAll();

    User selectByLoginName(String loginName);
}