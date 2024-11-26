package com.ruoyi.system.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.system.domain.bo.SysLoginBo;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.domain.vo.SysLoginVo;
import com.ruoyi.system.domain.vo.SysMenuVo;
import com.ruoyi.system.service.SysLoginService;
import com.ruoyi.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * $SysLoginController
 *
 * @author Link
 */
@Tag(name = "后管登陆管理", description = "后管页面登陆管理")
@RestController
@RequiredArgsConstructor
public class SysLoginController {
    
    private final SysLoginService sysLoginService;
    
    private final SysMenuService sysMenuService;
    
    @Operation(summary = "后管登陆", description = "后管页面登陆")
    @PostMapping("/login")
    public Result<SaTokenInfo> login(@RequestBody SysLoginBo param) {
        Long userId = sysLoginService.login(param.getUsername(), param.getPassword());
        StpUtil.login(userId);
        
        return ResultData.success(StpUtil.getTokenInfo());
    }
    
    @Operation(summary = "获取用户基本信息", description = "后管登录成功，获取用户基本信息(基本信息、角色信息、权限信息)")
    @GetMapping("/getUserInfo")
    public Result<SysLoginVo> getUserInfo() {
        SysLoginVo userInfo = sysLoginService.getUserInfo(1L);
        return ResultData.success(userInfo);
    }
    
    @Operation(summary = "后管登录用户菜单列表", description = "后管登录成功，返回菜单列表")
    @GetMapping("/listRouter")
    public Result<List<RouterVo>> listRouter() {
        List<SysMenuVo> SysMenuVoList = sysMenuService.listTreeByUserId(1L);
        return ResultData.success(sysMenuService.build(SysMenuVoList));
    }
    
    @Operation(summary = "后管退出登录", description = "后管退出登录")
    @PostMapping("/logout")
    public void logout() {
        StpUtil.logout();
    }
    
    
}
