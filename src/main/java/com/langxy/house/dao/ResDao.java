package com.langxy.house.dao;

import com.langxy.house.pojo.Res;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDao {

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateName") Long updateName);

    int insert(Res record);

    Res selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Res record);

    int inserts(List<Res> resList);

    int updateHouseId(List<Res> resList);

    List<Long> selectIdByHouseId(Long houseId);

    List<Res> selectByHouseId(Long houseId);
}