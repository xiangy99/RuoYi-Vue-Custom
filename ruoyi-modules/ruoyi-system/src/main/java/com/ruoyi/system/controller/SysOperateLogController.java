package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.query.SysOperateLogQuery;
import com.ruoyi.system.domain.vo.SysOperateLogVo;
import com.ruoyi.system.service.SysOperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * $SysOperateLogController
 *
 * @author Link
 */
@Tag(name = "操作日志管理", description = "操作日志管理")
@RestController
@RequestMapping("/system/operateLog")
@RequiredArgsConstructor
public class SysOperateLogController {
    
    private final SysOperateLogService sysOperateLogService;
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping("/page")
    public Result<PageLight<SysOperateLogVo>> page(@RequestBody SysOperateLogQuery param) {
        return ResultData.success(sysOperateLogService.page(param));
    }
}
