package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.system.domain.bo.SysDeptModifyBo;
import com.ruoyi.system.domain.bo.SysDeptSaveBo;
import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.domain.vo.SysDeptVo;
import com.ruoyi.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
 * $SysDeptController
 *
 * @author Link
 */
@Tag(name = "部门管理", description = "部门管理")
@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
public class SysDeptController {
    
    private final SysDeptService sysDeptService;
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "部门管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysDeptSaveBo param) {
        sysDeptService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "deptId", description = "部门ID", in = ParameterIn.PATH)})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{deptId}")
    @Log(title = "部门管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("deptId") Long deptId) {
        sysDeptService.delete(deptId);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "部门管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysDeptModifyBo param) {
        sysDeptService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "deptId", description = "部门ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{deptId}")
    public Result<SysDeptVo> get(@PathVariable("deptId") Long deptId) {
        return ResultData.success(sysDeptService.get(deptId));
    }
    
    @Operation(summary = "列表", description = "列表")
    @GetMapping("/list")
    public Result<List<SysDeptVo>> list(@RequestBody SysDeptQuery param) {
        return ResultData.success(sysDeptService.list(param));
    }
    
    /**
     * 查询部门列表（排除节点）
     */
    @Parameters(value = {@Parameter(name = "deptId", description = "部门ID", in = ParameterIn.PATH)})
    @Operation(summary = "列表(排除节点)", description = "列表(排除自身ID)")
    @GetMapping("/list/exclude/{deptId}")
    public Result<List<SysDeptVo>> excludeChild(@PathVariable("deptId") Long deptId) {
        List<SysDeptVo> list = sysDeptService.list(new SysDeptQuery());
        list.removeIf(
                v -> v.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(v.getAncestors(), ","),
                        deptId + ""));
        return ResultData.success(list);
    }
}
