package com.langxy.house.dto.street;

import lombok.Data;

@Data
public class StreetModifyDto {

    private Long id;

    private Long areaId;

    private String streetName;

    private Long updateUser;

}
