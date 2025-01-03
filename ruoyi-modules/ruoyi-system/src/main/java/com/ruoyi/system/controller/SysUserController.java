package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.core.vo.TreeNode;
import com.ruoyi.common.excel.core.ExcelResult;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.satoken.utils.SecurityUtils;
import com.ruoyi.system.domain.bo.SysUserModifyBo;
import com.ruoyi.system.domain.bo.SysUserSaveBo;
import com.ruoyi.system.domain.bo.UpdateUserStatusBo;
import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.domain.query.SysPostQuery;
import com.ruoyi.system.domain.query.SysRoleQuery;
import com.ruoyi.system.domain.query.SysUserQuery;
import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.system.domain.vo.SysRoleVo;
import com.ruoyi.system.domain.vo.SysUserVo;
import com.ruoyi.system.domain.vo.export.SysUserImportVo;
import com.ruoyi.system.importlistener.SysUserImportListener;
import com.ruoyi.system.service.SysDeptService;
import com.ruoyi.system.service.SysPostService;
import com.ruoyi.system.service.SysRoleService;
import com.ruoyi.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * $SysUserController
 *
 * @author Link
 */
@Tag(name = "用户管理", description = "用户管理")
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class SysUserController {
    
    private final SysUserService sysUserService;
    
    private final SysDeptService sysDeptService;
    
    private final SysRoleService sysRoleService;
    
    private final SysPostService sysPostService;
    
    @Operation(summary = "保存", description = "保存")
    @Log(title = "用户管理", businessType = LogBusinessTypeEnum.SAVE)
    @PostMapping
    public ResultData save(@RequestBody SysUserSaveBo param) {
        sysUserService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "userId", description = "用户ID", in = ParameterIn.PATH)})
    @Operation(summary = "删除", description = "删除")
    @Log(title = "用户管理", businessType = LogBusinessTypeEnum.DELETE)
    @DeleteMapping("/{userId}")
    public ResultData delete(@PathVariable("userId") Long userId) {
        sysUserService.delete(userId);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @Log(title = "用户管理", businessType = LogBusinessTypeEnum.MODIFY)
    @PutMapping
    public ResultData modify(@RequestBody SysUserModifyBo param) {
        sysUserService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "postId", description = "用户ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping({"/", "/{userId}"})
    public ResultData get(@PathVariable(value = "userId", required = false) Long userId) {
        List<SysRoleVo> sysRoleVoList = sysRoleService.list(new SysRoleQuery());
        
        Map<String, Object> vo = new HashMap<>();
        
        // 可选择的角色列表
        List<SysRoleVo> roleList = SecurityUtils.isSuper(userId) ? sysRoleVoList
                : sysRoleVoList.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList());
        vo.put("roleList", roleList);
        
        // 可选择的岗位列表
        List<SysPostVo> postList = sysPostService.list(new SysPostQuery());
        vo.put("postList", postList);
        
        if (userId != null) {
            SysUserVo userVo = sysUserService.get(userId);
            vo.put("user", userVo);
            SysUserVo infoWithRoleAndPost = sysUserService.getInfoWithRoleAndPost(userId);
            // 用户所属岗位ID列表(用来做回显)
            vo.put("userPostIdList",
                    Optional.ofNullable(infoWithRoleAndPost.getPostList()).orElse(Collections.emptyList()).stream()
                            .map(SysPostVo::getPostId).collect(Collectors.toList()));
            // 用户所属角色ID列表(用来做回显)
            vo.put("userRoleIdList",
                    Optional.ofNullable(infoWithRoleAndPost.getRoleList()).orElse(Collections.emptyList()).stream()
                            .map(SysRoleVo::getRoleId).collect(Collectors.toList()));
        }
        return ResultData.success(vo);
    }
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping("/page")
    public Result<PageLight<SysUserVo>> page(@RequestBody SysUserQuery param) {
        return ResultData.success(sysUserService.page(param));
    }
    
    @Operation(summary = "部门树", description = "部门树")
    @GetMapping("/listDeptTree")
    public Result<List<TreeNode>> listDeptTree(@RequestBody SysDeptQuery param) {
        return ResultData.success(sysDeptService.listDeptTree(param));
    }
    
    @Operation(summary = "修改用户状态", description = "修改用户状态")
    @Log(title = "用户管理", businessType = LogBusinessTypeEnum.MODIFY)
    @PutMapping("/updateStatus")
    public ResultData updateStatus(@RequestBody UpdateUserStatusBo param) {
        sysUserService.updateStatus(param.getUserId(), param.getStatus());
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "userId", description = "用户ID", in = ParameterIn.PATH)})
    @Operation(summary = "用户角色权限信息", description = "查询用户角色权限信息")
    @GetMapping("/authRole/{userId}")
    public ResultData authRole(@PathVariable("userId") Long userId) {
        Map<String, Object> vo = new HashMap<>();
        
        SysUserVo user = sysUserService.get(userId);
        List<SysRoleVo> roles = sysRoleService.listRoleByUserId(userId);
        vo.put("user", user);
        vo.put("roleList", SecurityUtils.isSuper(userId) ? roles
                : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return ResultData.success(vo);
    }
    
    @Operation(summary = "用户授权角色", description = "用户授权角色")
    @Log(title = "用户管理", businessType = LogBusinessTypeEnum.GRANT)
    @PutMapping("/authRole")
    public ResultData authRole(Long userId, Long[] roleIds) {
        sysUserService.authRole(userId, roleIds);
        return ResultData.success();
    }
    
    /**
     * 导入数据
     *
     * @param file          导入文件
     * @param updateSupport 是否更新已存在数据
     */
    @Operation(summary = "导入", description = "导入")
    @Log(title = "用户管理", businessType = LogBusinessTypeEnum.IMPORT)
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultData importData(@RequestPart("file") MultipartFile file, boolean updateSupport) throws Exception {
        ExcelResult<SysUserImportVo> result = ExcelUtil.importExcel(file.getInputStream(), SysUserImportVo.class,
                new SysUserImportListener(updateSupport));
        return ResultData.success(result.getAnalysis());
    }
    
}
