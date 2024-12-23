package com.ruoyi.system.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * $SysUserSaveBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserSaveBo implements Serializable {
    
    private static final long serialVersionUID = 1422749195175459379L;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 部门ID
     */
    private Long deptId;
    
    /**
     * 用户账号
     */
    @Size(max = 30, message = "用户账号最大长度要小于 30")
    @NotBlank(message = "用户账号不能为空")
    private String userName;
    
    /**
     * 用户昵称
     */
    @Size(max = 30, message = "用户昵称最大长度要小于 30")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    
    /**
     * 用户类型（00系统用户）
     */
    @Size(max = 2, message = "用户类型（00系统用户）最大长度要小于 2")
    private String userType;
    
    /**
     * 用户邮箱
     */
    @Size(max = 50, message = "用户邮箱最大长度要小于 50")
    private String email;
    
    /**
     * 手机号码
     */
    @Size(max = 11, message = "手机号码最大长度要小于 11")
    private String phonenumber;
    
    /**
     * 用户性别（0男 1女 2未知）
     */
    @Size(max = 1, message = "用户性别（0男 1女 2未知）最大长度要小于 1")
    private String sex;
    
    /**
     * 头像地址
     */
    @Size(max = 100, message = "头像地址最大长度要小于 100")
    private String avatar;
    
    /**
     * 密码
     */
    @Size(max = 100, message = "密码最大长度要小于 100")
    private String password;
    
    /**
     * 帐号状态（0正常 1停用）
     */
    @Size(max = 1, message = "帐号状态（0正常 1停用）最大长度要小于 1")
    private String status;
    
    /**
     * 是否删除
     */
    private Boolean isDeleted;
    
    /**
     * 最后登录IP
     */
    @Size(max = 128, message = "最后登录IP最大长度要小于 128")
    private String loginIp;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;
    
    /**
     * 创建者
     */
    @Size(max = 64, message = "创建者最大长度要小于 64")
    private String createBy;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新者
     */
    @Size(max = 64, message = "更新者最大长度要小于 64")
    private String updateBy;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 备注
     */
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
    
    private List<Long> postIdList;
    
    private List<Long> roleIdList;
    
}
