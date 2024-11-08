package com.ruoyi.system.domain.pojo;

import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 操作日志记录
 */
@Schema(description = "操作日志记录")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysOperateLog extends BaseEntity implements Serializable {
    
    /**
     * 日志主键
     */
    @Schema(description = "日志主键")
    @NotNull(message = "日志主键不能为null")
    private Long operateId;
    
    /**
     * 模块标题
     */
    @Schema(description = "模块标题")
    @Size(max = 50, message = "模块标题最大长度要小于 50")
    private String title;
    
    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;
    
    /**
     * 方法名称
     */
    @Schema(description = "方法名称")
    @Size(max = 300, message = "方法名称最大长度要小于 300")
    private String method;
    
    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    @Size(max = 10, message = "请求方式最大长度要小于 10")
    private String requestMethod;
    
    /**
     * 操作人类别（0其它 1后台用户 2手机端用户）
     */
    @Schema(description = "操作人类别（0其它 1后台用户 2手机端用户）")
    private Integer operatorType;
    
    /**
     * 操作人员
     */
    @Schema(description = "操作人员")
    @Size(max = 50, message = "操作人员最大长度要小于 50")
    private String operatorName;
    
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @Size(max = 50, message = "部门名称最大长度要小于 50")
    private String deptName;
    
    /**
     * 请求URL
     */
    @Schema(description = "请求URL")
    @Size(max = 255, message = "请求URL最大长度要小于 255")
    private String url;
    
    /**
     * 主机地址
     */
    @Schema(description = "主机地址")
    @Size(max = 128, message = "主机地址最大长度要小于 128")
    private String ip;
    
    /**
     * 操作地点
     */
    @Schema(description = "操作地点")
    @Size(max = 255, message = "操作地点最大长度要小于 255")
    private String location;
    
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    @Size(max = 2000, message = "请求参数最大长度要小于 2000")
    private String param;
    
    /**
     * 返回参数
     */
    @Schema(description = "返回参数")
    @Size(max = 2000, message = "返回参数最大长度要小于 2000")
    private String jsonResult;
    
    /**
     * 请求耗时
     */
    @Schema(description = "请求耗时")
    private Long costTime;
    
    /**
     * 操作状态（0成功 1失败）
     */
    @Schema(description = "操作状态（0成功 1失败）")
    @Size(max = 1, message = "操作状态（0成功 1失败）最大长度要小于 1")
    private String operateStatus;
    
    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    @Size(max = 2000, message = "错误消息最大长度要小于 2000")
    private String errorMsg;
    
    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private LocalDateTime operateTime;
}