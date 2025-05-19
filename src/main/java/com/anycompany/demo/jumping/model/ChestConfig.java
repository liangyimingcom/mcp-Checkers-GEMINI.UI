package com.anycompany.demo.jumping.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 宝箱配置实体类
 */
public class ChestConfig implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    
    /**
     * 宝箱ID
     */
    private String chestId;
    
    /**
     * 活动ID
     */
    private String activityId;
    
    /**
     * 宝箱名称
     */
    private String name;
    
    /**
     * 所需积分
     */
    private Integer pointsRequired;
    
    /**
     * 奖励描述
     */
    private String reward;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * CSS类名
     */
    private String cssClass;
    
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

    public String getChestId() {
        return chestId;
    }

    public void setChestId(String chestId) {
        this.chestId = chestId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(Integer pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
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
        return "ChestConfig{" +
                "id=" + id +
                ", chestId='" + chestId + '\'' +
                ", activityId='" + activityId + '\'' +
                ", name='" + name + '\'' +
                ", pointsRequired=" + pointsRequired +
                ", reward='" + reward + '\'' +
                ", icon='" + icon + '\'' +
                ", cssClass='" + cssClass + '\'' +
                ", version=" + version +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}