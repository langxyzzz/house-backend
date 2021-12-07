package com.langxy.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langxy.house.commons.Page;
import com.langxy.house.dao.UserDao;
import com.langxy.house.dto.user.UserGetPageDto;
import com.langxy.house.dto.user.UserInsertDto;
import com.langxy.house.dto.user.UserModifyDto;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.User;
import com.langxy.house.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao dao;

    @Override
    public void insert(UserInsertDto dto) {
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

}
