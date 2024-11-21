package com.ruoyi.demo.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.demo.domain.pojo.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.service.TestDemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Link
 * @date 2024-11-07
 */
@Tag(name = "TestDemo测试", description = "TestDemoController测试")
@RestController
@RequestMapping("/testDemo")
public class TestDemoController {
    
    private final TestDemoService testDemoService;
    
    public TestDemoController(TestDemoService testDemoService) {
        this.testDemoService = testDemoService;
    }
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    public ResultData save() {
        TestDemo testDemo = new TestDemo();
        testDemoService.save(testDemo);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    public ResultData update() {
        TestDemo testDemo = testDemoService.getById(3569377419200696320L);
        testDemo.setCode(System.currentTimeMillis());
        // 必须设置版本号到实体，乐观锁才会生效。字段需要添加@Version
        testDemo.setVersion(testDemo.getVersion());
        testDemoService.updateById(testDemo);
        return ResultData.success();
    }
    
    @Operation(summary = "删除", description = "根据ID删除")
    @DeleteMapping
    public ResultData del(@RequestParam Long id) {
        // 逻辑删除。字段需要添加@TableLogic
        testDemoService.removeById(id);
        return ResultData.success();
    }
    
    @Operation(summary = "详情", description = "根据ID获取详情")
    @GetMapping
    public Result<TestDemoVo> get(Long id) {
        return Result.success(testDemoService.getVo(id));
    }
    
    @Operation(summary = "分页", description = "查询分页列表")
    @Log(title = "分页", businessType = LogBusinessTypeEnum.OTHER)
    @GetMapping("/page")
    public Result<PageLight<TestDemo>> page() {
        return Result.success(testDemoService.page());
    }
}
