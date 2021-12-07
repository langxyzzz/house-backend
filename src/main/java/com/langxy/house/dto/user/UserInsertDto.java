package com.langxy.house.dto.user;

import lombok.Data;

@Data
public class UserInsertDto {

    private String loginName;

    private String loginPwd;

    private String realName;

    private Long createUser;

}
