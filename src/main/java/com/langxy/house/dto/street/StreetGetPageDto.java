package com.langxy.house.dto.street;

import lombok.Data;

@Data
public class StreetGetPageDto {

    public StreetGetPageDto() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    private int pageNum;

    private int pageSize;

}
