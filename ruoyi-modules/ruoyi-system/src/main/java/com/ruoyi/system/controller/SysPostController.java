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

/**
 * $SysPostController
 *
 * @author Link
 */
@Tag(name = "岗位管理", description = "岗位管理")
@RestController
@RequestMapping("/system/post")
@RequiredArgsConstructor
public class SysPostController {
    
    private final SysPostService sysPostService;
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysPostSaveBo param) {
        sysPostService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "postIds", description = "岗位ID数组", in = ParameterIn.PATH)})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{postIds}")
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("postIds") Long[] postIds) {
        sysPostService.delete(postIds);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysPostModifyBo param) {
        sysPostService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "postId", description = "岗位ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{postId}")
    public Result<SysPostVo> get(@PathVariable("postId") Long postId) {
        return ResultData.success(sysPostService.get(postId));
    }
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping("/page")
    public Result<PageLight<SysPostVo>> page(@RequestBody SysPostQuery param) {
        return ResultData.success(sysPostService.page(param));
    }
    
    @Operation(summary = "导出", description = "导出")
    @Log(title = "岗位管理", businessType = LogBusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody SysPostQuery param) {
        PageLight<SysPostVo> page = sysPostService.page(param);
        ExcelUtil.exportExcel(page.getRows(), "岗位数据", SysPostVo.class, response);
    }
}
