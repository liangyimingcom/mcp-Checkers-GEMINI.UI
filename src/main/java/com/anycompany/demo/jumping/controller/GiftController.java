package com.anycompany.demo.jumping.controller;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.anycompany.demo.jumping.common.result.ResponseResult;
import com.anycompany.demo.jumping.message.model.GiftMessage;
import com.anycompany.demo.jumping.message.sender.GiftMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 礼物控制器，处理礼物相关的请求
 */
@RestController
@RequestMapping("/api/v1/gifts")
public class GiftController {

    private static final Logger logger = LoggerFactory.getLogger(GiftController.class);
    
    private final GiftMessageSender giftMessageSender;
    
    @Autowired
    public GiftController(GiftMessageSender giftMessageSender) {
        this.giftMessageSender = giftMessageSender;
    }
    
    /**
     * 模拟送礼接口
     * 
     * @param userId 用户ID
     * @param giftId 礼物ID
     * @param giftValue 礼物价值
     * @param amount 礼物数量
     * @return 响应结果
     */
    @PostMapping("/send")
    public ResponseResult<Map<String, Object>> sendGift(
            @RequestParam("user_id") Long userId,
            @RequestParam("gift_id") Long giftId,
            @RequestParam("gift_value") Integer giftValue,
            @RequestParam("amount") Integer amount) {
        
        logger.info("收到送礼请求: userId={}, giftId={}, giftValue={}, amount={}", 
                userId, giftId, giftValue, amount);
        
        // 参数验证
        if (userId == null || userId <= 0) {
            return ResponseResult.error(400, "用户ID不能为空且必须大于0");
        }
        if (giftId == null || giftId <= 0) {
            return ResponseResult.error(400, "礼物ID不能为空且必须大于0");
        }
        if (giftValue == null || giftValue <= 0) {
            return ResponseResult.error(400, "礼物价值不能为空且必须大于0");
        }
        if (amount == null || amount <= 0) {
            return ResponseResult.error(400, "礼物数量不能为空且必须大于0");
        }
        
        try {
            // 创建礼物消息
            GiftMessage giftMessage = new GiftMessage(userId, giftId, giftValue, amount);
            
            // 发送到SQS队列
            SendMessageResult result = giftMessageSender.sendGiftMessage(giftMessage);
            
            // 构建响应数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("message_id", result.getMessageId());
            responseData.put("user_id", userId);
            responseData.put("gift_id", giftId);
            responseData.put("timestamp", giftMessage.getTimestamp());
            
            logger.info("礼物消息发送成功: messageId={}", result.getMessageId());
            return ResponseResult.success(responseData);
            
        } catch (Exception e) {
            logger.error("送礼失败", e);
            return ResponseResult.error(500, "送礼失败: " + e.getMessage());
        }
    }
}