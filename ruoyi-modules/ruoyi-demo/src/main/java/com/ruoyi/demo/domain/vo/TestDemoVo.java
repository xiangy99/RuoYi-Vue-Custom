package com.ruoyi.demo.domain.vo;

import com.ruoyi.demo.domain.pojo.TestDemo;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

/**
 * @author Link
 * @date 2024-11-07
 */
@Data
@AutoMapper(target = TestDemo.class)
public class TestDemoVo extends TestDemo {

}
