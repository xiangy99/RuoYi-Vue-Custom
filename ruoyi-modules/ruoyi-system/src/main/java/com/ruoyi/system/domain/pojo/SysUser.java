package com.ruoyi.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
 * 用户信息表
 */
@Schema(description = "用户信息表")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity implements Serializable {
    
    /**
     * 用户ID
     */
    @TableId
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为null")
    private Long userId;
    
    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;
    
    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    @Size(max = 30, message = "用户账号最大长度要小于 30")
    @NotBlank(message = "用户账号不能为空")
    private String userName;
    
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    @Size(max = 30, message = "用户昵称最大长度要小于 30")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    
    /**
     * 用户类型（00系统用户）
     */
    @Schema(description = "用户类型（00系统用户）")
    @Size(max = 2, message = "用户类型（00系统用户）最大长度要小于 2")
    private String userType;
    
    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    @Size(max = 50, message = "用户邮箱最大长度要小于 50")
    private String email;
    
    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    @Size(max = 15, message = "手机号码最大长度要小于 15")
    private String phonenumber;
    
    /**
     * 用户性别（0男 1女 2未知）
     */
    @Schema(description = "用户性别（0男 1女 2未知）")
    @Size(max = 1, message = "用户性别（0男 1女 2未知）最大长度要小于 1")
    private String sex;
    
    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    @Size(max = 100, message = "头像地址最大长度要小于 100")
    private String avatar;
    
    /**
     * 密码
     */
    @Schema(description = "密码")
    @Size(max = 100, message = "密码最大长度要小于 100")
    private String password;
    
    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    @Size(max = 128, message = "最后登录IP最大长度要小于 128")
    private String loginIp;
    
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;
    
    /**
     * 帐号状态（0正常 1停用）
     */
    @Schema(description = "帐号状态（0正常 1停用）")
    @Size(max = 1, message = "帐号状态（0正常 1停用）最大长度要小于 1")
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