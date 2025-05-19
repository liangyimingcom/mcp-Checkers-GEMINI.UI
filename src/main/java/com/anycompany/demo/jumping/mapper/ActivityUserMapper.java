package com.anycompany.demo.jumping.mapper;

import com.anycompany.demo.jumping.model.ActivityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Date;

/**
 * 活动用户表数据访问接口
 */
@Mapper
public interface ActivityUserMapper {
    
    /**
     * 根据ID查询活动用户
     * @param id ID
     * @return 活动用户信息
     */
    ActivityUser getById(@Param("id") Long id);
    
    /**
     * 根据用户ID和活动ID查询活动用户
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 活动用户信息
     */
    ActivityUser getByUserIdAndActivityId(@Param("userId") Long userId, @Param("activityId") String activityId);
    
    /**
     * 根据用户ID查询活动用户列表
     * @param userId 用户ID
     * @return 活动用户列表
     */
    List<ActivityUser> findByUserId(@Param("userId") Long userId);
    
    /**
     * 根据活动ID查询活动用户列表
     * @param activityId 活动ID
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 活动用户列表
     */
    List<ActivityUser> findByActivityId(@Param("activityId") String activityId, @Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 根据积分区间查询活动用户列表
     * @param activityId 活动ID
     * @param minPoints 最小积分
     * @param maxPoints 最大积分
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 活动用户列表
     */
    List<ActivityUser> findByPointsRange(
            @Param("activityId") String activityId, 
            @Param("minPoints") Integer minPoints, 
            @Param("maxPoints") Integer maxPoints,
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 根据积分排名查询活动用户列表
     * @param activityId 活动ID
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 活动用户列表（按积分降序排列）
     */
    List<ActivityUser> findOrderByPointsDesc(@Param("activityId") String activityId, @Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 查询活动中当日游戏次数已达上限的用户
     * @param activityId 活动ID
     * @param dailyPlayLimit 每日游戏次数限制
     * @param today 当天日期（用于比较最后游戏时间）
     * @return 活动用户列表
     */
    List<ActivityUser> findReachedDailyLimit(
            @Param("activityId") String activityId, 
            @Param("dailyPlayLimit") Integer dailyPlayLimit,
            @Param("today") Date today);
    
    /**
     * 插入活动用户
     * @param activityUser 活动用户信息
     * @return 影响行数
     */
    int insert(ActivityUser activityUser);
    
    /**
     * 更新活动用户
     * @param activityUser 活动用户信息
     * @return 影响行数
     */
    int update(ActivityUser activityUser);
    
    /**
     * 增加游戏机会
     * @param id 活动用户ID
     * @param chances 增加的机会数量
     * @return 影响行数
     */
    int increaseChances(@Param("id") Long id, @Param("chances") Integer chances);
    
    /**
     * 减少游戏机会
     * @param id 活动用户ID
     * @param chances 减少的机会数量
     * @return 影响行数
     */
    int decreaseChances(@Param("id") Long id, @Param("chances") Integer chances);
    
    /**
     * 增加积分
     * @param id 活动用户ID
     * @param points 增加的积分
     * @return 影响行数
     */
    int increasePoints(@Param("id") Long id, @Param("points") Integer points);
    
    /**
     * 减少积分
     * @param id 活动用户ID
     * @param points 减少的积分
     * @return 影响行数
     */
    int decreasePoints(@Param("id") Long id, @Param("points") Integer points);
    
    /**
     * 更新用户位置
     * @param id 活动用户ID
     * @param position 新位置
     * @return 影响行数
     */
    int updatePosition(@Param("id") Long id, @Param("position") Integer position);
    
    /**
     * 记录游戏次数和更新最后游戏时间
     * @param id 活动用户ID
     * @param playTime 游戏时间
     * @return 影响行数
     */
    int recordGamePlay(@Param("id") Long id, @Param("playTime") Date playTime);
    
    /**
     * 重置每日游戏次数
     * @param activityId 活动ID
     * @return 影响行数
     */
    int resetDailyPlays(@Param("activityId") String activityId);
    
    /**
     * 更新已解锁道具
     * @param id 活动用户ID
     * @param unlockedItems 已解锁道具(JSON格式)
     * @return 影响行数
     */
    int updateUnlockedItems(@Param("id") Long id, @Param("unlockedItems") String unlockedItems);
    
    /**
     * 更新已兑换宝箱
     * @param id 活动用户ID
     * @param redeemedChests 已兑换宝箱(JSON格式)
     * @return 影响行数
     */
    int updateRedeemedChests(@Param("id") Long id, @Param("redeemedChests") String redeemedChests);
    
    /**
     * 删除活动用户（逻辑删除）
     * @param id ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 批量删除活动用户（逻辑删除）
     * @param ids ID数组
     * @return 影响行数
     */
    int batchDelete(@Param("ids") Long[] ids);
}