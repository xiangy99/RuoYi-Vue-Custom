package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysPostModifyBo;
import com.ruoyi.system.domain.bo.SysPostSaveBo;
import com.ruoyi.system.domain.query.SysPostQuery;
import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.system.service.SysPostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * $SysPostController
 *
 * @author Link
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController {
    
    private final SysPostService sysPostService;
    
    public SysPostController(SysPostService sysPostService) {
        this.sysPostService = sysPostService;
    }
    
    @PostMapping
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysPostSaveBo param) {
        sysPostService.save(param);
        return ResultData.success();
    }
    
    @DeleteMapping("/{postIds}")
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("postIds") Long[] postIds) {
        sysPostService.delete(postIds);
        return ResultData.success();
    }
    
    @PutMapping
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysPostModifyBo param) {
        sysPostService.modify(param);
        return ResultData.success();
    }
    
    @GetMapping("/{postId}")
    public Result<SysPostVo> get(@PathVariable("postId") Long postId) {
        return ResultData.success(sysPostService.get(postId));
    }
    
    @GetMapping("/page")
    public Result<PageLight<SysPostVo>> page(SysPostQuery param) {
        return ResultData.success(sysPostService.page(param));
    }
    
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPostQuery param) {
        PageLight<SysPostVo> page = sysPostService.page(param);
        ExcelUtil.exportExcel(page.getRows(), "岗位数据", SysPostVo.class, response);
    }
}
