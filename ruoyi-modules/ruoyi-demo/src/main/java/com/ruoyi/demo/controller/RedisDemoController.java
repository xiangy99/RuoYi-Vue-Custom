package com.ruoyi.demo.controller;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.redis.utils.CacheUtil;
import com.ruoyi.common.redis.utils.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Link
 * @date 2024-11-19
 */
@Tag(name = "RedisController", description = "RedisUtil和CacheUtil测试")
@RestController
@RequestMapping("/redisDemo")
public class RedisDemoController {
    
    @Operation(summary = "set", description = "set到redis")
    @PostMapping("/set")
    public ResultData set() {
        RedisUtil.setCacheObject("demo1", CollUtil.toList(1, 2, 3));
        RedisUtil.setCacheObject("demo1:date", new Date());
        return ResultData.success();
    }
    
    @Operation(summary = "get", description = "根据key获取内容")
    @GetMapping
    public Object get(@RequestParam String key) {
        Date cacheObject = RedisUtil.getCacheObject(key);
        return ResultData.success(cacheObject);
    }
    
    @Operation(summary = "cacheableDemo1", description = "cacheable测试")
    @Cacheable(value = "cacheableDemo1", key = "#value")
    @GetMapping("/cacheableDemo1")
    public Object cacheableDemo1(Object value) {
        return ResultData.success(value);
    }
    
    @Operation(summary = "cacheableDemo2", description = "cacheable测试")
    @Cacheable(value = "cacheableDemo2")
    @GetMapping("/cacheableDemo2")
    public Object cacheableDemo2(Object value) {
        return ResultData.success(value);
    }
    
    @Operation(summary = "CacheUtil测试插入", description = "CacheUtil测试插入")
    @GetMapping("/cacheableDemo3")
    public Object cacheableDemo3() {
        CacheUtil.put("cacheUtilTest", "testKey", CollUtil.toList(1, 2));
        return ResultData.success();
    }
    
    @Operation(summary = "CacheUtil测试查询", description = "CacheUtil测试查询")
    @GetMapping("/cacheableDemo4")
    public Object cacheableDemo4() {
        List<Integer> result = CacheUtil.get("cacheUtilTest", "testKey");
        return ResultData.success(result);
    }
}
