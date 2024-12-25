package com.ruoyi.demo.domain.vo;

import com.ruoyi.demo.domain.pojo.TestDemo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Link
 * @date 2024-11-07
 */
@Schema(description = "测试类Vo")
@Getter
@Setter
@AllArgsConstructor
public class TestDemoVo extends TestDemo {

}
