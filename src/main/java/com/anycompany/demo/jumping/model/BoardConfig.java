package com.anycompany.demo.jumping.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 棋盘配置实体类
 */
public class BoardConfig implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    
    /**
     * 活动ID
     */
    private String activityId;
    
    /**
     * 格子位置
     */
    private Integer position;
    
    /**
     * 格子类型(START/POINTS/ITEM/HOME)
     */
    private String squareType;
    
    /**
     * 格子名称
     */
    private String name;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * 积分值(当类型为POINTS时)
     */
    private Integer pointsValue;
    
    /**
     * 道具键名(当类型为ITEM时)
     */
    private String itemKey;
    
    /**
     * 配置版本号
     */
    private Integer version;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 是否删除
     */
    private Boolean isDeleted;
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getSquareType() {
        return squareType;
    }

    public void setSquareType(String squareType) {
        this.squareType = squareType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(Integer pointsValue) {
        this.pointsValue = pointsValue;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "BoardConfig{" +
                "id=" + id +
                ", activityId='" + activityId + '\'' +
                ", position=" + position +
                ", squareType='" + squareType + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", pointsValue=" + pointsValue +
                ", itemKey='" + itemKey + '\'' +
                ", version=" + version +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}