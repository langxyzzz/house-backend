package com.langxy.house.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * house_t
 * @author 
 */
@Data
public class House implements Serializable {
    private Long id;

    /**
     * 区域
     */
    private Long areaId;

    /**
     * 街道
     */
    private Long streetId;

    /**
     * 小区名称
     */
    private Long communityId;

    /**
     * 地址
     */
    private String address;

    /**
     * 单元号
     */
    private String unitNumber;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 朝向
     */
    private String towards;

    /**
     * 面积
     */
    private String flatNumber;

    /**
     * 装修程度
     */
    private String furnishLevel;

    /**
     * 房屋年限
     */
    private Integer roomYear;

    /**
     * 产权年限
     */
    private Integer propertyYear;

    /**
     * 价钱
     */
    private String price;

    /**
     * 税务情况
     */
    private String taxSituation;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 联系电话
     */
    private Integer tel;

    /**
     * 房主
     */
    private String homeownerName;

    /**
     * 房主电话
     */
    private Integer homeownerTel;

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