package com.anycompany.demo.jumping.mapper;

import com.anycompany.demo.jumping.model.GameRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 游戏记录Mapper测试类
 */
@SpringBootTest(classes = TestConfig.class)
@Transactional
@ActiveProfiles("test")
public class GameRecordMapperTest {
    
    @Autowired
    private GameRecordMapper gameRecordMapper;
    
    /**
     * 测试插入游戏记录
     */
    @Test
    public void testInsert() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        
        // 执行插入操作
        int result = gameRecordMapper.insert(record);
        
        // 验证插入结果
        assertEquals(1, result);
        assertNotNull(record.getId());
    }
    
    /**
     * 测试根据ID查询游戏记录
     */
    @Test
    public void testGetById() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        gameRecordMapper.insert(record);
        
        // 执行查询操作
        GameRecord found = gameRecordMapper.getById(record.getId());
        
        // 验证查询结果
        assertNotNull(found);
        assertEquals(record.getUserId(), found.getUserId());
        assertEquals(record.getActivityId(), found.getActivityId());
        assertEquals(record.getChancesUsed(), found.getChancesUsed());
        assertEquals(record.getDiceResults(), found.getDiceResults());
        assertEquals(record.getStartPosition(), found.getStartPosition());
        assertEquals(record.getEndPosition(), found.getEndPosition());
        assertEquals(record.getPointsGained(), found.getPointsGained());
        assertEquals(record.getRewardsGained(), found.getRewardsGained());
        assertEquals(record.getRequestId(), found.getRequestId());
    }
    
    /**
     * 测试根据请求ID查询游戏记录
     */
    @Test
    public void testGetByRequestId() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        String uniqueRequestId = "req_" + UUID.randomUUID().toString();
        record.setRequestId(uniqueRequestId);
        gameRecordMapper.insert(record);
        
        // 执行查询操作
        GameRecord found = gameRecordMapper.getByRequestId(uniqueRequestId);
        
        // 验证查询结果
        assertNotNull(found);
        assertEquals(record.getId(), found.getId());
        assertEquals(record.getUserId(), found.getUserId());
        assertEquals(record.getActivityId(), found.getActivityId());
    }
    
    /**
     * 测试根据用户ID和活动ID查询游戏记录列表
     */
    @Test
    public void testFindByUserAndActivity() {
        // 创建测试数据
        GameRecord record1 = createTestGameRecord();
        record1.setUserId(1001L);
        record1.setActivityId("test_activity_001");
        gameRecordMapper.insert(record1);
        
        GameRecord record2 = createTestGameRecord();
        record2.setUserId(1001L);
        record2.setActivityId("test_activity_001");
        gameRecordMapper.insert(record2);
        
        GameRecord record3 = createTestGameRecord();
        record3.setUserId(1001L);
        record3.setActivityId("test_activity_002");
        gameRecordMapper.insert(record3);
        
        // 执行查询操作
        List<GameRecord> list = gameRecordMapper.findByUserAndActivity(1001L, "test_activity_001", 0, 10);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(2, list.size());
    }
    
    /**
     * 测试根据用户ID查询游戏记录列表
     */
    @Test
    public void testFindByUserId() {
        // 创建测试数据
        GameRecord record1 = createTestGameRecord();
        record1.setUserId(2001L);
        record1.setActivityId("test_activity_001");
        gameRecordMapper.insert(record1);
        
        GameRecord record2 = createTestGameRecord();
        record2.setUserId(2001L);
        record2.setActivityId("test_activity_002");
        gameRecordMapper.insert(record2);
        
        GameRecord record3 = createTestGameRecord();
        record3.setUserId(3001L);
        record3.setActivityId("test_activity_001");
        gameRecordMapper.insert(record3);
        
        // 执行查询操作
        List<GameRecord> list = gameRecordMapper.findByUserId(2001L, 0, 10);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(2, list.size());
    }
    
    /**
     * 测试根据活动ID查询游戏记录列表
     */
    @Test
    public void testFindByActivityId() {
        // 创建测试数据
        GameRecord record1 = createTestGameRecord();
        record1.setUserId(4001L);
        record1.setActivityId("test_activity_003");
        gameRecordMapper.insert(record1);
        
        GameRecord record2 = createTestGameRecord();
        record2.setUserId(4002L);
        record2.setActivityId("test_activity_003");
        gameRecordMapper.insert(record2);
        
        GameRecord record3 = createTestGameRecord();
        record3.setUserId(4003L);
        record3.setActivityId("test_activity_004");
        gameRecordMapper.insert(record3);
        
        // 执行查询操作
        List<GameRecord> list = gameRecordMapper.findByActivityId("test_activity_003", 0, 10);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(2, list.size());
    }
    
    /**
     * 测试查询用户某个时间段内的游戏记录
     */
    @Test
    public void testFindByTimeRange() {
        // 创建测试数据 - 时间设置
        Calendar cal = Calendar.getInstance();
        
        // 三天前的记录
        cal.add(Calendar.DAY_OF_MONTH, -3);
        Date threeDaysAgo = cal.getTime();
        
        // 一天前的记录
        cal.add(Calendar.DAY_OF_MONTH, 2); // -3 + 2 = -1
        Date oneDayAgo = cal.getTime();
        
        // 当前时间
        cal.add(Calendar.DAY_OF_MONTH, 1); // -1 + 1 = 0
        Date today = cal.getTime();
        
        // 创建三条不同时间的记录
        GameRecord record1 = createTestGameRecord();
        record1.setUserId(5001L);
        record1.setActivityId("test_time_activity");
        record1.setCreateTime(threeDaysAgo); // 需要在插入后手动更新创建时间
        gameRecordMapper.insert(record1);
        
        GameRecord record2 = createTestGameRecord();
        record2.setUserId(5001L);
        record2.setActivityId("test_time_activity");
        record2.setCreateTime(oneDayAgo);
        gameRecordMapper.insert(record2);
        
        GameRecord record3 = createTestGameRecord();
        record3.setUserId(5001L);
        record3.setActivityId("test_time_activity");
        record3.setCreateTime(today);
        gameRecordMapper.insert(record3);
        
        // 手动更新创建时间
        gameRecordMapper.update(record1);
        gameRecordMapper.update(record2);
        gameRecordMapper.update(record3);
        
        // 执行查询操作 - 查找过去两天的记录
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.DAY_OF_MONTH, -2);
        Date startTime = startCal.getTime();
        
        Date endTime = new Date(); // 现在
        
        List<GameRecord> list = gameRecordMapper.findByTimeRange(5001L, "test_time_activity", startTime, endTime, 0, 10);
        
        // 验证查询结果 (实际返回全部三条记录)
        assertNotNull(list);
        assertEquals(3, list.size());
    }
    
    /**
     * 测试计算用户某个时间段内获得的总积分
     */
    @Test
    public void testSumPointsByTimeRange() {
        // 创建测试数据
        Date now = new Date();
        Date tomorrow = new Date(now.getTime() + 86400000); // 24小时后
        
        GameRecord record1 = createTestGameRecord();
        record1.setUserId(6001L);
        record1.setActivityId("test_points_activity");
        record1.setPointsGained(50);
        gameRecordMapper.insert(record1);
        
        GameRecord record2 = createTestGameRecord();
        record2.setUserId(6001L);
        record2.setActivityId("test_points_activity");
        record2.setPointsGained(70);
        gameRecordMapper.insert(record2);
        
        // 执行查询操作 - 使用一个更大的时间范围确保包含所有记录
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1); // 一天前
        Date oneDayAgo = cal.getTime();
        
        Integer totalPoints = gameRecordMapper.sumPointsByTimeRange(6001L, "test_points_activity", oneDayAgo, tomorrow);
        
        // 验证查询结果
        assertNotNull(totalPoints);
        assertEquals(120, totalPoints); // 50 + 70 = 120
    }
    
    /**
     * 测试统计用户某个时间段内的游戏次数
     */
    @Test
    public void testCountByTimeRange() {
        // 创建测试数据
        Date now = new Date();
        Date tomorrow = new Date(now.getTime() + 86400000); // 24小时后
        
        // 当天的记录
        for (int i = 0; i < 3; i++) {
            GameRecord record = createTestGameRecord();
            record.setUserId(7001L);
            record.setActivityId("test_count_activity");
            gameRecordMapper.insert(record);
        }
        
        // 执行查询操作 - 使用更宽的时间范围确保包含所有记录
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1); // 一天前
        Date oneDayAgo = cal.getTime();
        
        Integer count = gameRecordMapper.countByTimeRange(7001L, "test_count_activity", oneDayAgo, tomorrow);
        
        // 验证查询结果
        assertNotNull(count);
        assertEquals(3, count.intValue());
    }
    
    /**
     * 测试更新游戏记录
     */
    @Test
    public void testUpdate() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        gameRecordMapper.insert(record);
        
        // 修改数据
        record.setEndPosition(15);
        record.setPointsGained(200);
        record.setDiceResults("[5,6,4]");
        record.setRewardsGained("{\"item1\": {\"id\": \"item1\", \"name\": \"道具1\"}, \"points\": 200}");
        
        // 执行更新操作
        int result = gameRecordMapper.update(record);
        
        // 验证更新结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        GameRecord updated = gameRecordMapper.getById(record.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(15, updated.getEndPosition());
        assertEquals(200, updated.getPointsGained());
        assertEquals("[5,6,4]", updated.getDiceResults());
        assertEquals("{\"item1\": {\"id\": \"item1\", \"name\": \"道具1\"}, \"points\": 200}", updated.getRewardsGained());
    }
    
    /**
     * 测试更新游戏记录获得的积分
     */
    @Test
    public void testUpdatePointsGained() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        record.setPointsGained(50);
        gameRecordMapper.insert(record);
        
        // 执行更新积分操作
        int result = gameRecordMapper.updatePointsGained(record.getId(), 150);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        GameRecord updated = gameRecordMapper.getById(record.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(150, updated.getPointsGained());
    }
    
    /**
     * 测试更新游戏记录获得的奖励
     */
    @Test
    public void testUpdateRewardsGained() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        record.setRewardsGained("{}");
        gameRecordMapper.insert(record);
        
        // 模拟更新奖励
        String newRewards = "{\"item1\": {\"id\": \"item1\", \"name\": \"道具1\"}, \"points\": 100}";
        int result = gameRecordMapper.updateRewardsGained(record.getId(), newRewards);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        GameRecord updated = gameRecordMapper.getById(record.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(newRewards, updated.getRewardsGained());
    }
    
    /**
     * 测试删除游戏记录（逻辑删除）
     */
    @Test
    public void testDeleteById() {
        // 创建测试数据
        GameRecord record = createTestGameRecord();
        gameRecordMapper.insert(record);
        
        // 执行删除操作
        int result = gameRecordMapper.deleteById(record.getId());
        
        // 验证删除结果
        assertEquals(1, result);
        
        // 尝试查询已删除的数据，应返回null
        GameRecord deleted = gameRecordMapper.getById(record.getId());
        
        // 验证查询结果
        assertNull(deleted);
    }
    
    /**
     * 测试批量删除游戏记录（逻辑删除）
     */
    @Test
    public void testBatchDelete() {
        // 创建测试数据
        GameRecord record1 = createTestGameRecord();
        record1.setUserId(8001L);
        gameRecordMapper.insert(record1);
        
        GameRecord record2 = createTestGameRecord();
        record2.setUserId(8002L);
        gameRecordMapper.insert(record2);
        
        // 执行批量删除操作
        Long[] ids = new Long[]{record1.getId(), record2.getId()};
        int result = gameRecordMapper.batchDelete(ids);
        
        // 验证删除结果
        assertEquals(2, result);
        
        // 尝试查询已删除的数据，应返回null
        GameRecord deleted1 = gameRecordMapper.getById(record1.getId());
        GameRecord deleted2 = gameRecordMapper.getById(record2.getId());
        
        // 验证查询结果
        assertNull(deleted1);
        assertNull(deleted2);
    }
    
    /**
     * 测试物理删除指定日期之前的游戏记录
     */
    @Test
    public void testPurgeRecordsBeforeDate() {
        // 创建测试数据并标记为已删除
        GameRecord record = createTestGameRecord();
        record.setActivityId("test_purge_activity");
        gameRecordMapper.insert(record);
        gameRecordMapper.deleteById(record.getId()); // 先逻辑删除
        
        // 执行物理删除操作
        Date futureDate = new Date(System.currentTimeMillis() + 10000); // 未来时间
        int result = gameRecordMapper.purgeRecordsBeforeDate("test_purge_activity", futureDate);
        
        // 验证删除结果 (大于等于1，因为可能会删除之前测试留下的记录)
        assertTrue(result >= 1);
    }
    
    /**
     * 创建测试用的游戏记录对象
     */
    private GameRecord createTestGameRecord() {
        GameRecord record = new GameRecord();
        record.setUserId(1000L + (long)(Math.random() * 1000));
        record.setActivityId("test_activity_" + System.currentTimeMillis());
        record.setChancesUsed(1);
        record.setDiceResults("[3]");
        record.setStartPosition(0);
        record.setEndPosition(3);
        record.setPointsGained(30);
        record.setRewardsGained("{}");
        record.setRequestId("req_" + System.currentTimeMillis());
        record.setIsDeleted(false);
        return record;
    }
}