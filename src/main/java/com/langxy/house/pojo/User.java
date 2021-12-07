package com.langxy.house.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_t
 * @author 
 */
@Data
public class User implements Serializable {
    private Long id;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 登陆密码
     */
    private String loginPwd;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 角色
     */
    private Integer role;

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

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}