package com.ruoyi.demo.controller;

import com.ruoyi.common.core.result.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Link
 */

@RestController
@RequestMapping("/demo/hello")
public class HelloController {
    
    @GetMapping
    public String hello() {
        return "hello";
    }
    
    @GetMapping("/test1")
    public ResultData test1() {
        return ResultData.success();
    }
    
    @GetMapping("/test2")
    public Object test2(String url) {
        return url;
    }
    
    @PostMapping("/test3")
    public Object test3(@RequestBody Map<String, Object> param) {
        return param;
    }
    
    @GetMapping("/test4")
    public Object test4() {
        return 1848553513497415680L;
    }
}
