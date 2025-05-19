package com.anycompany.demo.jumping.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动用户实体类
 */
public class ActivityUser implements Serializable {
    
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
     * 当前位置
     */
    private Integer currentPosition;
    
    /**
     * 游戏机会
     */
    private Integer gameChances;
    
    /**
     * 积分
     */
    private Integer points;
    
    /**
     * 每日已玩次数
     */
    private Integer dailyPlays;
    
    /**
     * 最后游戏时间
     */
    private Date lastPlayTime;
    
    /**
     * 已解锁道具(JSON格式)
     */
    private String unlockedItems;
    
    /**
     * 已兑换宝箱(JSON格式)
     */
    private String redeemedChests;
    
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

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Integer getGameChances() {
        return gameChances;
    }

    public void setGameChances(Integer gameChances) {
        this.gameChances = gameChances;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getDailyPlays() {
        return dailyPlays;
    }

    public void setDailyPlays(Integer dailyPlays) {
        this.dailyPlays = dailyPlays;
    }

    public Date getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(Date lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public String getUnlockedItems() {
        return unlockedItems;
    }

    public void setUnlockedItems(String unlockedItems) {
        this.unlockedItems = unlockedItems;
    }

    public String getRedeemedChests() {
        return redeemedChests;
    }

    public void setRedeemedChests(String redeemedChests) {
        this.redeemedChests = redeemedChests;
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
        return "ActivityUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", activityId='" + activityId + '\'' +
                ", currentPosition=" + currentPosition +
                ", gameChances=" + gameChances +
                ", points=" + points +
                ", dailyPlays=" + dailyPlays +
                ", lastPlayTime=" + lastPlayTime +
                ", unlockedItems='" + unlockedItems + '\'' +
                ", redeemedChests='" + redeemedChests + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}