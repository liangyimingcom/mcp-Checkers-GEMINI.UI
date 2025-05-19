package com.anycompany.demo.jumping.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏记录实体类
 */
public class GameRecord implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 活动ID
     */
    private String activityId;
    
    /**
     * 使用机会数
     */
    private Integer chancesUsed;
    
    /**
     * 骰子结果(JSON格式)
     */
    private String diceResults;
    
    /**
     * 起始位置
     */
    private Integer startPosition;
    
    /**
     * 结束位置
     */
    private Integer endPosition;
    
    /**
     * 获得积分
     */
    private Integer pointsGained;
    
    /**
     * 获得奖励(JSON格式)
     */
    private String rewardsGained;
    
    /**
     * 请求ID(防重)
     */
    private String requestId;
    
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getChancesUsed() {
        return chancesUsed;
    }

    public void setChancesUsed(Integer chancesUsed) {
        this.chancesUsed = chancesUsed;
    }

    public String getDiceResults() {
        return diceResults;
    }

    public void setDiceResults(String diceResults) {
        this.diceResults = diceResults;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public Integer getPointsGained() {
        return pointsGained;
    }

    public void setPointsGained(Integer pointsGained) {
        this.pointsGained = pointsGained;
    }

    public String getRewardsGained() {
        return rewardsGained;
    }

    public void setRewardsGained(String rewardsGained) {
        this.rewardsGained = rewardsGained;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
        return "GameRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", activityId='" + activityId + '\'' +
                ", chancesUsed=" + chancesUsed +
                ", diceResults='" + diceResults + '\'' +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", pointsGained=" + pointsGained +
                ", rewardsGained='" + rewardsGained + '\'' +
                ", requestId='" + requestId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}