package com.anycompany.demo.jumping.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

/**
 * 测试环境配置类
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@ComponentScan(basePackages = "com.anycompany.demo.jumping")
@MapperScan("com.anycompany.demo.jumping.mapper")
@ActiveProfiles("test")
public class TestConfig {
    
}