package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysConfig;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@AutoMapper(target = SysConfig.class)
@Schema(description = "参数配置表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysConfigVo extends SysConfig {

}
