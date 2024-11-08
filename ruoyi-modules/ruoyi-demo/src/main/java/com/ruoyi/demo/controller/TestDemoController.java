package com.ruoyi.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.core.utils.ServletUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.demo.domain.pojo.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.service.TestDemoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.mybatis.domain.PageSupport.ORDERS;

/**
 * @author Link
 * @date 2024-11-07
 */
@RestController
@RequestMapping("/testDemo")
public class TestDemoController {
    
    private final TestDemoService testDemoService;
    
    public TestDemoController(TestDemoService testDemoService) {
        this.testDemoService = testDemoService;
    }
    
    @PostMapping
    public ResultData save() {
        TestDemo testDemo = new TestDemo();
        testDemoService.save(testDemo);
        return ResultData.success();
    }
    
    @PutMapping
    public ResultData update() {
        TestDemo testDemo = testDemoService.getById(3569377419200696320L);
        testDemo.setCode(System.currentTimeMillis());
        // 必须设置版本号到实体，乐观锁才会生效。字段需要添加@Version
        testDemo.setVersion(testDemo.getVersion());
        testDemoService.updateById(testDemo);
        return ResultData.success();
    }
    
    @DeleteMapping
    public ResultData del(@RequestParam Long id) {
        // 逻辑删除。字段需要添加@TableLogic
        testDemoService.removeById(id);
        return ResultData.success();
    }
    
    @GetMapping
    public Result<TestDemoVo> get(Long id) {
        
        String orderListStr = ServletUtil.getParameter(ORDERS);
        List<OrderItem> orderItems = JSONObject.parseArray(orderListStr, OrderItem.class);
        return Result.success(testDemoService.getVo(id));
    }
    
    @GetMapping("/page")
    public Result<PageLight<TestDemo>> page() {
        return Result.success(testDemoService.page());
    }
}
