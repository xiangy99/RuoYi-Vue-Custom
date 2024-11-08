package com.ruoyi.system.domain.pojo;

import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ${DESCRIPTION}
 *
 * @author Link 
 * @date 2024-11-08 
 * 
 */

/**
 * 定时任务调度日志表
 */
@Schema(description = "定时任务调度日志表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysJobLog extends BaseEntity implements Serializable {
    
    /**
     * 任务日志ID
     */
    @Schema(description = "任务日志ID")
    @NotNull(message = "任务日志ID不能为null")
    private Long jobLogId;
    
    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    @Size(max = 64, message = "任务名称最大长度要小于 64")
    @NotBlank(message = "任务名称不能为空")
    private String jobName;
    
    /**
     * 任务组名
     */
    @Schema(description = "任务组名")
    @Size(max = 64, message = "任务组名最大长度要小于 64")
    @NotBlank(message = "任务组名不能为空")
    private String jobGroup;
    
    /**
     * 调用目标字符串
     */
    @Schema(description = "调用目标字符串")
    @Size(max = 500, message = "调用目标字符串最大长度要小于 500")
    @NotBlank(message = "调用目标字符串不能为空")
    private String invokeTarget;
    
    /**
     * 日志信息
     */
    @Schema(description = "日志信息")
    @Size(max = 500, message = "日志信息最大长度要小于 500")
    private String jobMessage;
    
    /**
     * 执行状态（0正常 1失败）
     */
    @Schema(description = "执行状态（0正常 1失败）")
    @Size(max = 1, message = "执行状态（0正常 1失败）最大长度要小于 1")
    private String status;
    
    /**
     * 异常信息
     */
    @Schema(description = "异常信息")
    @Size(max = 2000, message = "异常信息最大长度要小于 2000")
    private String exceptionInfo;
}