package com.langxy.house.dto.community;

import lombok.Data;

@Data
public class CommunityGetPageDto {

    public CommunityGetPageDto() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    private int pageNum;

    private int pageSize;

}
