package com.anycompany.demo.jumping.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动配置实体类
 */
public class ActivityConfig implements Serializable {
    
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
     * 开始时间
     */
    private Date startTime;
    
    /**
     * 结束时间
     */
    private Date endTime;
    
    /**
     * 棋盘大小
     */
    private Integer boardSize;
    
    /**
     * 单次最大使用机会
     */
    private Integer maxChancesPerRoll;
    
    /**
     * 每日游戏次数限制
     */
    private Integer dailyPlayLimit;
    
    /**
     * 是否激活
     */
    private Boolean isActive;
    
    /**
     * 配置版本号
     */
    private Integer version;
    
    /**
     * 配置生效时间
     */
    private Date effectiveTime;
    
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(Integer boardSize) {
        this.boardSize = boardSize;
    }

    public Integer getMaxChancesPerRoll() {
        return maxChancesPerRoll;
    }

    public void setMaxChancesPerRoll(Integer maxChancesPerRoll) {
        this.maxChancesPerRoll = maxChancesPerRoll;
    }

    public Integer getDailyPlayLimit() {
        return dailyPlayLimit;
    }

    public void setDailyPlayLimit(Integer dailyPlayLimit) {
        this.dailyPlayLimit = dailyPlayLimit;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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
        return "ActivityConfig{" +
                "id=" + id +
                ", activityId='" + activityId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", boardSize=" + boardSize +
                ", maxChancesPerRoll=" + maxChancesPerRoll +
                ", dailyPlayLimit=" + dailyPlayLimit +
                ", isActive=" + isActive +
                ", version=" + version +
                ", effectiveTime=" + effectiveTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}