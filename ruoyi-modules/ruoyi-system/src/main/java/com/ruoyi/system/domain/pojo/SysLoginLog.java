package com.ruoyi.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */

/**
 * 系统访问记录
 */
@Schema(description = "系统访问记录")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysLoginLog extends BaseEntity implements Serializable {
    
    /**
     * 访问ID
     */
    @TableId
    @Schema(description = "访问ID")
    @NotNull(message = "访问ID不能为null")
    private Long infoId;
    
    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    @Size(max = 50, message = "用户账号最大长度要小于 50")
    private String userName;
    
    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址")
    @Size(max = 128, message = "登录IP地址最大长度要小于 128")
    private String ipaddr;
    
    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    @Size(max = 255, message = "登录地点最大长度要小于 255")
    private String loginLocation;
    
    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    @Size(max = 50, message = "浏览器类型最大长度要小于 50")
    private String browser;
    
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    @Size(max = 50, message = "操作系统最大长度要小于 50")
    private String os;
    
    /**
     * 登录状态（0成功 1失败）
     */
    @Schema(description = "登录状态（0成功 1失败）")
    @Size(max = 1, message = "登录状态（0成功 1失败）最大长度要小于 1")
    private String status;
    
    /**
     * 提示消息
     */
    @Schema(description = "提示消息")
    @Size(max = 255, message = "提示消息最大长度要小于 255")
    private String msg;
    
    /**
     * 访问时间
     */
    @Schema(description = "访问时间")
    private LocalDateTime loginTime;
}