package com.langxy.house.service;

import com.langxy.house.commons.Page;
import com.langxy.house.dto.user.UserGetPageDto;
import com.langxy.house.dto.user.UserInsertDto;
import com.langxy.house.dto.user.UserLoginDto;
import com.langxy.house.dto.user.UserModifyDto;
import com.langxy.house.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * 用户 service 接口
 *
 * @author langxy
 */
public interface IUserService {

    /**
     * 新增用户
     *
     * @param dto
     */
    void insert(UserInsertDto dto);

    /**
     * 修改用户信息
     *
     * @param dto
     */
    void modify(UserModifyDto dto);

    /**
     * 获取全部用户
     *
     * @return
     */
    List<Map<String, Object>> getAll();

    /**
     * 获取用户列表并分页
     *
     * @param dto
     * @return
     */
    Page<Map<String, Object>> getPage(UserGetPageDto dto);

    /**
     * 登陆
     *
     * @param dto
     * @return
     */
    User login(UserLoginDto dto);
}
