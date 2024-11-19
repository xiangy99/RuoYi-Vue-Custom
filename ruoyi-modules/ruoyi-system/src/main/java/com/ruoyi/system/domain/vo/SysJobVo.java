package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysJob;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "定时任务调度表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysJobVo extends SysJob {

}
