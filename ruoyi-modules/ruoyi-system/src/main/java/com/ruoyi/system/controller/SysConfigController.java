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
import jakarta.servlet.http.HttpServletResponse;
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
@RestController
@RequestMapping("/system/config")
public class SysConfigController {
    
    private final SysConfigService sysConfigService;
    
    public SysConfigController(SysConfigService sysConfigService) {
        this.sysConfigService = sysConfigService;
    }
    
    @PostMapping
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysConfigSaveBo param) {
        sysConfigService.save(param);
        return ResultData.success();
    }
    
    @DeleteMapping("/{configIds}")
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("configIds") Long[] configIds) {
        sysConfigService.delete(configIds);
        return ResultData.success();
    }
    
    @PutMapping
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysConfigModifyBo param) {
        sysConfigService.modify(param);
        return ResultData.success();
    }
    
    @GetMapping("/{configId}")
    public Result<SysConfigVo> get(@PathVariable("configId") Long configId) {
        return ResultData.success(sysConfigService.get(configId));
    }
    
    @GetMapping("/page")
    public Result<PageLight<SysConfigVo>> page(SysConfigQuery param) {
        return ResultData.success(sysConfigService.page(param));
    }
    
    @Log(title = "参数配置管理", businessType = LogBusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConfigQuery param) {
        List<SysConfigVo> list = sysConfigService.list(param);
        ExcelUtil.exportExcel(list, "参数配置", SysConfigVo.class, response);
    }
    
    /**
     * 刷新参数缓存
     */
    @Log(title = "参数管理", businessType = LogBusinessTypeEnum.CLEAN)
    @DeleteMapping("/refreshCache")
    public ResultData refreshCache() {
        sysConfigService.resetConfigCache();
        return ResultData.success();
    }
}
