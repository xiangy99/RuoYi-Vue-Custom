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
 * 定时任务调度表
 */
@Schema(description = "定时任务调度表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysJob extends BaseEntity implements Serializable {
    
    /**
     * 任务ID
     */
    @Schema(description = "任务ID")
    @NotNull(message = "任务ID不能为null")
    private Long jobId;
    
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
     * cron执行表达式
     */
    @Schema(description = "cron执行表达式")
    @Size(max = 255, message = "cron执行表达式最大长度要小于 255")
    private String cronExpression;
    
    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    @Schema(description = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    @Size(max = 20, message = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）最大长度要小于 20")
    private String misfirePolicy;
    
    /**
     * 是否并发执行（0允许 1禁止）
     */
    @Schema(description = "是否并发执行（0允许 1禁止）")
    @Size(max = 1, message = "是否并发执行（0允许 1禁止）最大长度要小于 1")
    private String concurrent;
    
    /**
     * 状态（0正常 1暂停）
     */
    @Schema(description = "状态（0正常 1暂停）")
    @Size(max = 1, message = "状态（0正常 1暂停）最大长度要小于 1")
    private String status;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
    
    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    private Boolean isDeleted;
}