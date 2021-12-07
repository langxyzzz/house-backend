package com.langxy.house.service.impl;

import com.langxy.house.commons.Constants;
import com.langxy.house.dao.ResDao;
import com.langxy.house.exception.CustomerException;
import com.langxy.house.pojo.Res;
import com.langxy.house.service.IResService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResServiceImpl implements IResService {

    @Resource
    private ResDao dao;

    @Override
    public List<Map<String, Object>> insert(List<MultipartFile> multipartFileList, Long createUser) {
        if (multipartFileList.size() == 0) {
            throw new CustomerException(500, "file can not null");
        }
        List<Map<String, Object>> data = new ArrayList<>();
        List<Res> resList = new ArrayList<>();
        multipartFileList.forEach(item -> {
            String filename = item.getOriginalFilename();
            File file = new File(Constants.FILE_PATH + filename);
            Res res = new Res();
            res.setFilename(filename);
            res.setRemotePath(Constants.FILE_PATH + filename);
            res.setCreateUser(createUser);
            resList.add(res);
            try {
                item.transferTo(file);
            } catch (IOException e) {
                throw new CustomerException(500, "file upload failure");
            }
        });

        int rowCount = dao.inserts(resList);
        if (rowCount == 0) {
            throw new CustomerException(500, "insert failure");
        }
        resList.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("path", Constants.REMOTE_PATH + item.getFilename());
            data.add(map);
        });
        return data;
    }

    @Override
    public int updateHouseId(List<Res> resList) {
        return dao.updateHouseId(resList);
    }

    @Override
    public List<Long> getIdByHouseId(Long houseId) {
        return dao.selectIdByHouseId(houseId);
    }

    @Override
    public List<Res> getByHouseId(Long houseId) {
        return dao.selectByHouseId(houseId);
    }

}
