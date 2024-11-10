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
@RestController
@RequiredArgsConstructor
public class SysLoginController {
    
    private final SysLoginService sysLoginService;
    
    private final SysMenuService sysMenuService;
    
    @PostMapping("/login")
    public Result<SaTokenInfo> login(@RequestBody SysLoginBo param) {
        Long userId = sysLoginService.login(param.getUsername(), param.getPassword());
        StpUtil.login(userId);
        
        return ResultData.success(StpUtil.getTokenInfo());
    }
    
    @GetMapping("/getUserInfo")
    public Result<SysLoginVo> getUserInfo() {
        SysLoginVo userInfo = sysLoginService.getUserInfo(1L);
        return ResultData.success(userInfo);
    }
    
    @GetMapping("/listRouter")
    public Result<List<RouterVo>> listRouter() {
        List<SysMenuVo> SysMenuVoList = sysMenuService.listTreeByUserId(1L);
        return ResultData.success(sysMenuService.build(SysMenuVoList));
    }
    
    @PostMapping("/logout")
    public void logout() {
        StpUtil.logout();
    }
    
    
}
