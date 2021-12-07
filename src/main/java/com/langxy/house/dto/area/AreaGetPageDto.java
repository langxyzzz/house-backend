package com.langxy.house.dto.area;

import lombok.Data;

@Data
public class AreaGetPageDto {

    public AreaGetPageDto() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    private int pageNum;

    private int pageSize;

}
