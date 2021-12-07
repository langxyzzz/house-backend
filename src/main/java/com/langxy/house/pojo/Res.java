package com.langxy.house.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * res_t
 * @author 
 */
@Data
public class Res implements Serializable {
    private Long id;

    /**
     * 房屋信息id
     */
    private Long houseId;

    /**
     * 路径
     */
    private String remotePath;

    /**
     * 文件名称
     */
    private String filename;

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

    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}