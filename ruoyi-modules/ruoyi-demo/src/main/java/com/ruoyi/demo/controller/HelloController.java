package com.ruoyi.demo.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Link
 */

@Tag(name = "返回结果封装测试", description = "HelloController测试")
@RestController
@RequestMapping("/demo/hello")
public class HelloController {
    
    @Operation(summary = "返回String字符串", description = "hello方法返回String字符串")
    @GetMapping(produces = "application/json; charset=UTF-8")
    public String hello() {
        return "hello";
    }
    
    @Operation(summary = "返回操作成功", description = "操作成功返回示例")
    @GetMapping("/test1")
    public ResultData test1() {
        return ResultData.success();
    }
    
    @Operation(summary = "返回传入URL", description = "返回传入参数示例")
    @GetMapping("/test2")
    public ResultData test2(@Parameter(name = "url", description = "请求url") String url) {
        return Result.success(url);
    }
    
    @Operation(summary = "返回超出最大Long类型试", description = "返回超出Long类型，结果通过String返回")
    @GetMapping("/test4")
    public Result<Long> test4() {
        return Result.success(1848553513497415680L);
    }
}
