package com.langxy.house.dto.community;

import lombok.Data;

@Data
public class CommunityModifyDto {

    private Long id;

    private Long streetId;

    private String communityName;

    private Long updateUser;

}
