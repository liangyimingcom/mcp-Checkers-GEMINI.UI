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