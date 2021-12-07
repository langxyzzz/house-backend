package com.langxy.house.service;

import com.langxy.house.pojo.Res;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IResService {

    List<Map<String, Object>> insert(List<MultipartFile> multipartFileList, Long createUser);

    int updateHouseId(List<Res> resList);

    List<Long> getIdByHouseId(Long id);

    List<Res> getByHouseId(Long houseId);
}
