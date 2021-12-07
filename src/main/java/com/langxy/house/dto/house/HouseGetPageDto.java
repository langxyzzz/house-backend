package com.langxy.house.dto.house;

import lombok.Data;

@Data
public class HouseGetPageDto {

    public HouseGetPageDto() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    private int pageNum;

    private int pageSize;
}
