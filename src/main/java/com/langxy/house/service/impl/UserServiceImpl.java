package com.langxy.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langxy.house.commons.Page;
import com.langxy.house.dao.UserDao;
import com.langxy.house.dto.user.UserGetPageDto;
import com.langxy.house.dto.user.UserInsertDto;
import com.langxy.house.dto.user.UserLoginDto;
import com.langxy.house.dto.user.UserModifyDto;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.User;
import com.langxy.house.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户 service 接口实现
 *
 * @author langxy
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao dao;

    @Override
    public void insert(UserInsertDto dto) {
        final User userItem = dao.selectByLoginName(dto.getLoginName());
        if (userItem != null) {
            throw new CustomerException(500, "当前登陆名已存在");
        }
        User user = new User();
        user.setLoginName(dto.getLoginName());
        user.setLoginPwd(dto.getLoginPwd());
        user.setRealName(dto.getRealName());
        user.setCreateUser(dto.getCreateUser());
        int rowCount = dao.insert(user);
        if (rowCount == 0) {
            throw new CustomerException(500, "insert failure");
        }
    }

    @Override
    public void modify(UserModifyDto dto) {
        User user = dao.selectByPrimaryKey(dto.getId());
        if (user == null) {
            throw new CustomerException(500, "this Id user is not found");
        }
        user.setLoginPwd(dto.getLoginPwd());
        user.setRealName(dto.getRealName());
        user.setUpdateUser(dto.getUpdateUser());
        int rowCount = dao.updateByPrimaryKey(user);
        if (rowCount == 0) {
            throw new CustomerException(500, "modify failure");
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return dao.selectAll();
    }

    @Override
    public Page<Map<String, Object>> getPage(UserGetPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Map<String, Object>> data = dao.selectAll();
        return Page.generatePage(new PageInfo<>(data));
    }

    @Override
    public User login(UserLoginDto dto) {
        User user = dao.selectByLoginName(dto.getLoginName());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!user.getLoginPwd().equals(dto.getLoginPwd())) {
            throw new RuntimeException("密码有误");
        }
        return user;
    }

}
