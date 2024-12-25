package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.AuthUserBo;
import com.ruoyi.system.domain.bo.CancelAuthUserBo;
import com.ruoyi.system.domain.bo.SysRoleModifyBo;
import com.ruoyi.system.domain.bo.SysRoleSaveBo;
import com.ruoyi.system.domain.pojo.SysUserRole;
import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.domain.query.SysRoleQuery;
import com.ruoyi.system.domain.query.SysUserQuery;
import com.ruoyi.system.domain.vo.SysRoleVo;
import com.ruoyi.system.domain.vo.SysUserVo;
import com.ruoyi.system.service.SysDeptService;
import com.ruoyi.system.service.SysRoleService;
import com.ruoyi.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * $SysRoleController
 *
 * @author Link
 */
@Tag(name = "角色管理", description = "角色管理")
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class SysRoleController {
    
    private final SysRoleService sysRoleService;
    
    private final SysDeptService sysDeptService;
    
    private final SysUserService sysUserService;
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysRoleSaveBo param) {
        sysRoleService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "roleIds", description = "角色ID数组", in = ParameterIn.PATH)})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{roleIds}")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("roleIds") Long[] roleIds) {
        sysRoleService.delete(roleIds);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysRoleModifyBo param) {
        sysRoleService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "roleId", description = "角色ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{roleId}")
    public Result<SysRoleVo> get(@PathVariable("roleId") Long roleId) {
        return ResultData.success(sysRoleService.get(roleId));
    }
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping("/page")
    public Result<PageLight<SysRoleVo>> page(@RequestBody SysRoleQuery param) {
        return ResultData.success(sysRoleService.page(param));
    }
    
    @Operation(summary = "导出", description = "导出")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoleQuery param) {
        List<SysRoleVo> list = sysRoleService.list(param);
        ExcelUtil.exportExcel(list, "角色数据", SysRoleVo.class, response);
    }
    
    @Operation(summary = "修改角色状态", description = "修改角色状态")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.MODIFY)
    @PutMapping("/updateStatus")
    public ResultData updateStatus(@RequestBody SysRoleModifyBo param) {
        return ResultData.success(sysRoleService.updateStatus(param.getRoleId(), param.getStatus()));
    }
    
    @Operation(summary = "获取对应角色部门树列表", description = "获取对应角色部门树列表")
    @GetMapping(value = "/deptTree/{roleId}")
    public Result<HashMap<String, Object>> deptTree(@PathVariable("roleId") Long roleId) {
        HashMap<String, Object> voMap = new HashMap<>();
        voMap.put("checkedKeys", sysDeptService.listDeptByRoleId(roleId));
        voMap.put("deptList", sysDeptService.listDeptTree(new SysDeptQuery()));
        
        return ResultData.success(voMap);
    }
    
    @Operation(summary = "修改数据权限信息", description = "修改数据权限信息")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.MODIFY)
    @PutMapping("/dataScope")
    public ResultData updateDataScope(@RequestBody SysRoleModifyBo param) {
        sysRoleService.updateDataScope(param);
        return ResultData.success();
    }
    
    /**
     * 查询角色的已分配用户列表
     */
    @Operation(summary = "查询角色的已分配用户列表", description = "查询角色的已分配用户列表")
    @GetMapping("/authUser/listAllocatedUser")
    public Result<PageLight<SysUserVo>> listAllocatedUser(SysUserQuery user) {
        PageLight<SysUserVo> list = sysUserService.listAllocatedUser(user);
        return ResultData.success(list);
    }
    
    /**
     * 查询角色的未分配用户列表
     */
    @Operation(summary = "查询角色的未分配用户列表", description = "查询角色的未分配用户列表")
    @GetMapping("/authUser/listUnAllocatedUser")
    public Result<PageLight<SysUserVo>> listUnAllocatedUser(SysUserQuery user) {
        PageLight<SysUserVo> list = sysUserService.listUnAllocatedUser(user);
        return ResultData.success(list);
    }
    
    @Operation(summary = "批量授权用户角色", description = "批量授权用户角色")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.GRANT)
    @PutMapping("/authUser/batchAuthUser")
    public ResultData batchAuthUser(@RequestBody AuthUserBo param) {
        sysRoleService.batchAuthUser(param.getUserIdList(), param.getRoleId());
        return ResultData.success();
    }
    
    @Operation(summary = "取消授权用户角色", description = "取消授权用户角色")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.GRANT)
    @PutMapping("/authUser/cancelAuthUser")
    public ResultData cancelAuthUser(@RequestBody SysUserRole param) {
        sysRoleService.cancelAuthUser(param.getUserId(), param.getRoleId());
        return ResultData.success();
    }
    
    
    @Operation(summary = "批量取消授权用户角色", description = "批量取消授权用户角色")
    @Log(title = "角色管理", businessType = LogBusinessTypeEnum.GRANT)
    @PutMapping("/authUser/batchCancelAuthUser")
    public ResultData batchCancelAuthUser(@RequestBody CancelAuthUserBo param) {
        sysRoleService.batchCancelAuthUser(param.getUserIdList(), param.getRoleId());
        return ResultData.success();
    }
}
