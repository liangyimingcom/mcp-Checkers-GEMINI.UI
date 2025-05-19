package com.anycompany.demo.jumping.mapper;

import com.anycompany.demo.jumping.model.ActivityUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 活动用户Mapper测试类
 */
@SpringBootTest(classes = TestConfig.class)
@Transactional
@ActiveProfiles("test")
public class ActivityUserMapperTest {
    
    @Autowired
    private ActivityUserMapper activityUserMapper;
    
    /**
     * 测试插入活动用户
     */
    @Test
    public void testInsert() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        
        // 执行插入操作
        int result = activityUserMapper.insert(user);
        
        // 验证插入结果
        assertEquals(1, result);
        assertNotNull(user.getId());
    }
    
    /**
     * 测试根据ID查询活动用户
     */
    @Test
    public void testGetById() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        activityUserMapper.insert(user);
        
        // 执行查询操作
        ActivityUser found = activityUserMapper.getById(user.getId());
        
        // 验证查询结果
        assertNotNull(found);
        assertEquals(user.getUserId(), found.getUserId());
        assertEquals(user.getActivityId(), found.getActivityId());
        assertEquals(user.getCurrentPosition(), found.getCurrentPosition());
        assertEquals(user.getPoints(), found.getPoints());
        assertEquals(user.getUnlockedItems(), found.getUnlockedItems());
        assertEquals(user.getRedeemedChests(), found.getRedeemedChests());
    }
    
    /**
     * 测试根据用户ID和活动ID查询活动用户
     */
    @Test
    public void testGetByUserIdAndActivityId() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        activityUserMapper.insert(user);
        
        // 执行查询操作
        ActivityUser found = activityUserMapper.getByUserIdAndActivityId(user.getUserId(), user.getActivityId());
        
        // 验证查询结果
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
        assertEquals(user.getCurrentPosition(), found.getCurrentPosition());
        assertEquals(user.getPoints(), found.getPoints());
    }
    
    /**
     * 测试根据用户ID查询活动用户列表
     */
    @Test
    public void testFindByUserId() {
        // 创建测试数据
        Long commonUserId = 1000L + (long)(Math.random() * 1000);
        
        ActivityUser user1 = createTestActivityUser();
        user1.setUserId(commonUserId);
        user1.setActivityId("test_act_001");
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setUserId(commonUserId);
        user2.setActivityId("test_act_002");
        activityUserMapper.insert(user2);
        
        // 执行查询操作
        List<ActivityUser> list = activityUserMapper.findByUserId(commonUserId);
        
        // 验证查询结果
        assertNotNull(list);
        assertTrue(list.size() >= 2, "应至少返回2条记录");
        boolean hasActivity001 = list.stream().anyMatch(u -> u.getActivityId().equals("test_act_001"));
        boolean hasActivity002 = list.stream().anyMatch(u -> u.getActivityId().equals("test_act_002"));
        assertTrue(hasActivity001, "应包含test_act_001活动记录");
        assertTrue(hasActivity002, "应包含test_act_002活动记录");
    }
    
    /**
     * 测试根据活动ID查询活动用户列表
     */
    @Test
    public void testFindByActivityId() {
        // 创建测试数据
        ActivityUser user1 = createTestActivityUser();
        user1.setUserId(1001L);
        user1.setActivityId("test_common_act");
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setUserId(1002L);
        user2.setActivityId("test_common_act");
        activityUserMapper.insert(user2);
        
        // 执行查询操作
        List<ActivityUser> list = activityUserMapper.findByActivityId("test_common_act", 0, 10);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(2, list.size());
    }
    
    /**
     * 测试根据积分区间查询活动用户列表
     */
    @Test
    public void testFindByPointsRange() {
        // 创建测试数据
        ActivityUser user1 = createTestActivityUser();
        user1.setUserId(2001L);
        user1.setActivityId("test_points_act");
        user1.setPoints(150);
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setUserId(2002L);
        user2.setActivityId("test_points_act");
        user2.setPoints(250);
        activityUserMapper.insert(user2);
        
        ActivityUser user3 = createTestActivityUser();
        user3.setUserId(2003L);
        user3.setActivityId("test_points_act");
        user3.setPoints(350);
        activityUserMapper.insert(user3);
        
        // 执行查询操作
        List<ActivityUser> list = activityUserMapper.findByPointsRange("test_points_act", 200, 300, 0, 10);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(250, list.get(0).getPoints());
    }
    
    /**
     * 测试根据积分排名查询活动用户列表
     */
    @Test
    public void testFindOrderByPointsDesc() {
        // 创建测试数据
        ActivityUser user1 = createTestActivityUser();
        user1.setUserId(3001L);
        user1.setActivityId("test_rank_act");
        user1.setPoints(100);
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setUserId(3002L);
        user2.setActivityId("test_rank_act");
        user2.setPoints(300);
        activityUserMapper.insert(user2);
        
        ActivityUser user3 = createTestActivityUser();
        user3.setUserId(3003L);
        user3.setActivityId("test_rank_act");
        user3.setPoints(200);
        activityUserMapper.insert(user3);
        
        // 执行查询操作
        List<ActivityUser> list = activityUserMapper.findOrderByPointsDesc("test_rank_act", 0, 10);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(300, list.get(0).getPoints()); // 第一名
        assertEquals(200, list.get(1).getPoints()); // 第二名
        assertEquals(100, list.get(2).getPoints()); // 第三名
    }
    
    /**
     * 测试查询活动中当日游戏次数已达上限的用户
     */
    @Test
    public void testFindReachedDailyLimit() {
        // 创建测试数据
        Date today = new Date();
        
        ActivityUser user1 = createTestActivityUser();
        user1.setUserId(4001L);
        user1.setActivityId("test_limit_act");
        user1.setDailyPlays(5);
        user1.setLastPlayTime(today);
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setUserId(4002L);
        user2.setActivityId("test_limit_act");
        user2.setDailyPlays(10);
        user2.setLastPlayTime(today);
        activityUserMapper.insert(user2);
        
        // 执行查询操作
        List<ActivityUser> list = activityUserMapper.findReachedDailyLimit("test_limit_act", 10, today);
        
        // 验证查询结果
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(user2.getUserId(), list.get(0).getUserId());
    }
    
    /**
     * 测试更新活动用户
     */
    @Test
    public void testUpdate() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        activityUserMapper.insert(user);
        
        // 修改数据
        user.setCurrentPosition(15);
        user.setGameChances(8);
        user.setPoints(250);
        user.setUnlockedItems("{\"item1\": true, \"item2\": true}");
        
        // 执行更新操作
        int result = activityUserMapper.update(user);
        
        // 验证更新结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(15, updated.getCurrentPosition());
        assertEquals(8, updated.getGameChances());
        assertEquals(250, updated.getPoints());
        assertEquals("{\"item1\": true, \"item2\": true}", updated.getUnlockedItems());
    }
    
    /**
     * 测试增加游戏机会
     */
    @Test
    public void testIncreaseChances() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setGameChances(5);
        activityUserMapper.insert(user);
        
        // 执行增加游戏机会操作
        int result = activityUserMapper.increaseChances(user.getId(), 3);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(8, updated.getGameChances());
    }
    
    /**
     * 测试减少游戏机会
     */
    @Test
    public void testDecreaseChances() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setGameChances(10);
        activityUserMapper.insert(user);
        
        // 执行减少游戏机会操作
        int result = activityUserMapper.decreaseChances(user.getId(), 4);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(6, updated.getGameChances());
    }
    
    /**
     * 测试增加积分
     */
    @Test
    public void testIncreasePoints() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setPoints(100);
        activityUserMapper.insert(user);
        
        // 执行增加积分操作
        int result = activityUserMapper.increasePoints(user.getId(), 50);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(150, updated.getPoints());
    }
    
    /**
     * 测试减少积分
     */
    @Test
    public void testDecreasePoints() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setPoints(200);
        activityUserMapper.insert(user);
        
        // 执行减少积分操作
        int result = activityUserMapper.decreasePoints(user.getId(), 75);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(125, updated.getPoints());
    }
    
    /**
     * 测试更新用户位置
     */
    @Test
    public void testUpdatePosition() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setCurrentPosition(5);
        activityUserMapper.insert(user);
        
        // 执行更新位置操作
        int result = activityUserMapper.updatePosition(user.getId(), 12);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(12, updated.getCurrentPosition());
    }
    
    /**
     * 测试记录游戏次数和更新最后游戏时间
     */
    @Test
    public void testRecordGamePlay() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setDailyPlays(3);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8); // 设置为今天的8点
        user.setLastPlayTime(cal.getTime());
        activityUserMapper.insert(user);
        
        // 执行记录游戏操作
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 10); // 设置为今天的10点
        Date playTime = now.getTime();
        int result = activityUserMapper.recordGamePlay(user.getId(), playTime);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(4, updated.getDailyPlays()); // 同一天玩，次数+1
        assertNotNull(updated.getLastPlayTime());
    }
    
    /**
     * 测试重置每日游戏次数
     */
    @Test
    public void testResetDailyPlays() {
        // 创建测试数据
        ActivityUser user1 = createTestActivityUser();
        user1.setActivityId("test_reset_act");
        user1.setDailyPlays(5);
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setActivityId("test_reset_act");
        user2.setDailyPlays(8);
        activityUserMapper.insert(user2);
        
        // 执行重置操作
        int result = activityUserMapper.resetDailyPlays("test_reset_act");
        
        // 验证操作结果
        assertEquals(2, result);
        
        // 查询更新后的数据
        ActivityUser updated1 = activityUserMapper.getById(user1.getId());
        ActivityUser updated2 = activityUserMapper.getById(user2.getId());
        
        // 验证更新结果
        assertNotNull(updated1);
        assertNotNull(updated2);
        assertEquals(0, updated1.getDailyPlays());
        assertEquals(0, updated2.getDailyPlays());
    }
    
    /**
     * 测试更新已解锁道具
     */
    @Test
    public void testUpdateUnlockedItems() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setUnlockedItems("{}");
        activityUserMapper.insert(user);
        
        // 模拟解锁新道具
        String newUnlockedItems = "{\"avatar_frame_a\": true, \"entry_effect_x\": true}";
        int result = activityUserMapper.updateUnlockedItems(user.getId(), newUnlockedItems);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(newUnlockedItems, updated.getUnlockedItems());
    }
    
    /**
     * 测试更新已兑换宝箱
     */
    @Test
    public void testUpdateRedeemedChests() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        user.setRedeemedChests("{}");
        activityUserMapper.insert(user);
        
        // 模拟兑换宝箱
        String newRedeemedChests = "{\"bronze\": true, \"silver\": true}";
        int result = activityUserMapper.updateRedeemedChests(user.getId(), newRedeemedChests);
        
        // 验证操作结果
        assertEquals(1, result);
        
        // 查询更新后的数据
        ActivityUser updated = activityUserMapper.getById(user.getId());
        
        // 验证更新结果
        assertNotNull(updated);
        assertEquals(newRedeemedChests, updated.getRedeemedChests());
    }
    
    /**
     * 测试删除活动用户（逻辑删除）
     */
    @Test
    public void testDeleteById() {
        // 创建测试数据
        ActivityUser user = createTestActivityUser();
        activityUserMapper.insert(user);
        
        // 执行删除操作
        int result = activityUserMapper.deleteById(user.getId());
        
        // 验证删除结果
        assertEquals(1, result);
        
        // 尝试查询已删除的数据，应返回null
        ActivityUser deleted = activityUserMapper.getById(user.getId());
        
        // 验证查询结果
        assertNull(deleted);
    }
    
    /**
     * 测试批量删除活动用户（逻辑删除）
     */
    @Test
    public void testBatchDelete() {
        // 创建测试数据
        ActivityUser user1 = createTestActivityUser();
        user1.setUserId(6001L);
        activityUserMapper.insert(user1);
        
        ActivityUser user2 = createTestActivityUser();
        user2.setUserId(6002L);
        activityUserMapper.insert(user2);
        
        // 执行批量删除操作
        Long[] ids = new Long[]{user1.getId(), user2.getId()};
        int result = activityUserMapper.batchDelete(ids);
        
        // 验证删除结果
        assertEquals(2, result);
        
        // 尝试查询已删除的数据，应返回null
        ActivityUser deleted1 = activityUserMapper.getById(user1.getId());
        ActivityUser deleted2 = activityUserMapper.getById(user2.getId());
        
        // 验证查询结果
        assertNull(deleted1);
        assertNull(deleted2);
    }
    
    /**
     * 创建测试用的活动用户对象
     */
    private ActivityUser createTestActivityUser() {
        ActivityUser user = new ActivityUser();
        user.setUserId(1000L + (long)(Math.random() * 1000));
        user.setActivityId("test_activity_" + System.currentTimeMillis());
        user.setCurrentPosition(0);
        user.setGameChances(5);
        user.setPoints(100);
        user.setDailyPlays(0);
        user.setLastPlayTime(new Date());
        user.setUnlockedItems("{}");
        user.setRedeemedChests("{}");
        user.setIsDeleted(false);
        return user;
    }
}