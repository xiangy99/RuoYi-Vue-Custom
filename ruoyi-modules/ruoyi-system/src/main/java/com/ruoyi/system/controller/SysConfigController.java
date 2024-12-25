package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysConfigModifyBo;
import com.ruoyi.system.domain.bo.SysConfigSaveBo;
import com.ruoyi.system.domain.query.SysConfigQuery;
import com.ruoyi.system.domain.vo.SysConfigVo;
import com.ruoyi.system.service.SysConfigService;
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

import java.util.List;

/**
 * $SysConfigController
 *
 * @author Link
 */
@Tag(name = "参数配置管理", description = "全局参数配置管理")
@RestController
@RequestMapping("/system/config")
@RequiredArgsConstructor
public class SysConfigController {
    
    private final SysConfigService sysConfigService;
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysConfigSaveBo param) {
        sysConfigService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "configIds", description = "参数配置数组", in = ParameterIn.PATH)})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{configIds}")
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("configIds") Long[] configIds) {
        sysConfigService.delete(configIds);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysConfigModifyBo param) {
        sysConfigService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "configId", description = "参数配置ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{configId}")
    public Result<SysConfigVo> get(@PathVariable("configId") Long configId) {
        return ResultData.success(sysConfigService.get(configId));
    }
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping("/page")
    public Result<PageLight<SysConfigVo>> page(@RequestBody SysConfigQuery param) {
        return ResultData.success(sysConfigService.page(param));
    }
    
    @Operation(summary = "导出", description = "导出")
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody SysConfigQuery param) {
        List<SysConfigVo> list = sysConfigService.list(param);
        ExcelUtil.exportExcel(list, "参数配置", SysConfigVo.class, response);
    }
    
    /**
     * 刷新参数缓存
     */
    @Operation(summary = "刷新缓存", description = "刷新缓存")
    @Log(title = "参数管理", businessType = LogBusinessTypeEnum.CLEAN)
    @DeleteMapping("/refreshCache")
    public ResultData refreshCache() {
        sysConfigService.resetConfigCache();
        return ResultData.success();
    }
}
