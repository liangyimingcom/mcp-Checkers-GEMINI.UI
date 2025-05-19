package com.anycompany.demo.jumping.mapper;

import com.anycompany.demo.jumping.model.GameRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 游戏记录数据访问接口
 */
@Mapper
public interface GameRecordMapper {
    
    /**
     * 根据ID查询游戏记录
     * @param id ID
     * @return 游戏记录信息
     */
    GameRecord getById(@Param("id") Long id);
    
    /**
     * 根据请求ID查询游戏记录（幂等性检查）
     * @param requestId 请求ID
     * @return 游戏记录信息
     */
    GameRecord getByRequestId(@Param("requestId") String requestId);
    
    /**
     * 根据用户ID和活动ID查询游戏记录列表
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 游戏记录列表
     */
    List<GameRecord> findByUserAndActivity(
            @Param("userId") Long userId, 
            @Param("activityId") String activityId,
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 根据用户ID查询游戏记录列表
     * @param userId 用户ID
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 游戏记录列表
     */
    List<GameRecord> findByUserId(
            @Param("userId") Long userId,
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 根据活动ID查询游戏记录列表
     * @param activityId 活动ID
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 游戏记录列表
     */
    List<GameRecord> findByActivityId(
            @Param("activityId") String activityId,
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 查询用户某个时间段内的游戏记录
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 游戏记录列表
     */
    List<GameRecord> findByTimeRange(
            @Param("userId") Long userId, 
            @Param("activityId") String activityId,
            @Param("startTime") Date startTime, 
            @Param("endTime") Date endTime,
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 计算用户某个时间段内获得的总积分
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 总积分
     */
    Integer sumPointsByTimeRange(
            @Param("userId") Long userId, 
            @Param("activityId") String activityId,
            @Param("startTime") Date startTime, 
            @Param("endTime") Date endTime);
    
    /**
     * 统计用户某个时间段内的游戏次数
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 游戏次数
     */
    Integer countByTimeRange(
            @Param("userId") Long userId, 
            @Param("activityId") String activityId,
            @Param("startTime") Date startTime, 
            @Param("endTime") Date endTime);
    
    /**
     * 插入游戏记录
     * @param gameRecord 游戏记录信息
     * @return 影响行数
     */
    int insert(GameRecord gameRecord);
    
    /**
     * 更新游戏记录
     * @param gameRecord 游戏记录信息
     * @return 影响行数
     */
    int update(GameRecord gameRecord);
    
    /**
     * 更新游戏记录获得的积分
     * @param id 游戏记录ID
     * @param pointsGained 获得的积分
     * @return 影响行数
     */
    int updatePointsGained(@Param("id") Long id, @Param("pointsGained") Integer pointsGained);
    
    /**
     * 更新游戏记录获得的奖励
     * @param id 游戏记录ID
     * @param rewardsGained 获得的奖励(JSON格式)
     * @return 影响行数
     */
    int updateRewardsGained(@Param("id") Long id, @Param("rewardsGained") String rewardsGained);
    
    /**
     * 删除游戏记录（逻辑删除）
     * @param id ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 批量删除游戏记录（逻辑删除）
     * @param ids ID数组
     * @return 影响行数
     */
    int batchDelete(@Param("ids") Long[] ids);
    
    /**
     * 物理删除指定日期之前的游戏记录（用于归档清理）
     * @param activityId 活动ID
     * @param beforeDate 日期界限
     * @return 影响行数
     */
    int purgeRecordsBeforeDate(@Param("activityId") String activityId, @Param("beforeDate") Date beforeDate);
}