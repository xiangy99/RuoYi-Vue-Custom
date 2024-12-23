package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.query.SysOperateLogQuery;
import com.ruoyi.system.domain.vo.SysOperateLogVo;
import com.ruoyi.system.service.SysOperateLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * $SysOperateLogController
 *
 * @author Link
 */
@RestController
@RequestMapping("/system/operateLog")
public class SysOperateLogController {
    
    private final SysOperateLogService sysOperateLogService;
    
    public SysOperateLogController(SysOperateLogService sysOperateLogService) {
        this.sysOperateLogService = sysOperateLogService;
    }
    
    @GetMapping("/page")
    public Result<PageLight<SysOperateLogVo>> page(SysOperateLogQuery param) {
        return ResultData.success(sysOperateLogService.page(param));
    }
}
