package com.ruoyi.demo.domain.vo;

import com.ruoyi.demo.domain.pojo.TestDemo;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-07
 */
@AutoMapper(target = TestDemo.class)
@Schema(description = "测试类Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class TestDemoVo extends TestDemo {

}
