package com.langxy.house.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * community_t
 * @author 
 */
@Data
public class Community implements Serializable {
    private Long id;

    /**
     * 街道id
     */
    private Long streetId;

    /**
     * 小区名称
     */
    private String communityName;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}