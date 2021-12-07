package com.langxy.house.dto.user;

import lombok.Data;

@Data
public class UserModifyDto {

    private Long id;

    private String loginPwd;

    private String realName;

    private Long updateUser;

}
