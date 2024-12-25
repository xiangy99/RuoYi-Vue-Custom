package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "用户信息表 Vo")
@Getter
@Setter
public class SysUserVo extends SysUser {
    
    /**
     * 用户所在部门列表
     */
    private List<SysDeptVo> deptList;
    
    /**
     * 用户所属角色列表
     */
    private List<SysRoleVo> roleList;
    
    /**
     * 用户所属岗位列表
     */
    private List<SysPostVo> postList;
    
    /**
     * 部门名称
     */
    private String deptName;
}
