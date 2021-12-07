package com.langxy.house.dto.user;

import lombok.Data;

@Data
public class UserGetPageDto {

    public UserGetPageDto() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    private int pageNum;

    private int pageSize;

}
