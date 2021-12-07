package com.langxy.house.dto.community;

import lombok.Data;

@Data
public class CommunityInsertDto {

    private Long streetId;

    private String communityName;

    private Long createUser;

}
