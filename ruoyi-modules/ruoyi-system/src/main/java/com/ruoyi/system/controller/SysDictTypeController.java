package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysDictTypeModifyBo;
import com.ruoyi.system.domain.bo.SysDictTypeSaveBo;
import com.ruoyi.system.domain.query.SysDictTypeQuery;
import com.ruoyi.system.domain.vo.SysDictTypeVo;
import com.ruoyi.system.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * @author Link
 * @date 2024-11-19
 */
@Tag(name = "字典类型管理", description = "字典类型管理")
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {
    
    private final SysDictTypeService sysDictTypeService;
    
    public SysDictTypeController(SysDictTypeService sysDictTypeService) {
        this.sysDictTypeService = sysDictTypeService;
    }
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "字典类型管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysDictTypeSaveBo param) {
        sysDictTypeService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {
            @Parameter(name = "dictTypeIds", description = "字典类型ID数组", in = ParameterIn.PATH, example = "1,2")})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{dictTypeIds}")
    @Log(title = "字典类型管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("dictTypeIds") Long[] dictTypeIds) {
        sysDictTypeService.delete(dictTypeIds);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "字典类型管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysDictTypeModifyBo param) {
        sysDictTypeService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "dictTypeId", description = "字典类型ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{dictTypeId}")
    public Result<SysDictTypeVo> get(@PathVariable("dictTypeId") Long dictTypeId) {
        return ResultData.success(sysDictTypeService.get(dictTypeId));
    }
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping("/page")
    public Result<PageLight<SysDictTypeVo>> page(@RequestBody SysDictTypeQuery param) {
        return ResultData.success(sysDictTypeService.page(param));
    }
    
    /**
     * 刷新字典缓存
     */
    @Operation(summary = "清空缓存", description = "清空字段缓存")
    @Log(title = "字典类型", businessType = LogBusinessTypeEnum.CLEAN)
    @DeleteMapping("/refreshCache")
    public ResultData refreshCache() {
        sysDictTypeService.resetDictCache();
        return ResultData.success();
    }
    
    /**
     * 获取字典选择框列表
     */
    @Operation(summary = "获取字典选择框列表", description = "下拉选择框展示所有字典类型")
    @GetMapping("/optionselect")
    public ResultData optionselect() {
        List<SysDictTypeVo> sysDictTypeList = sysDictTypeService.list(new SysDictTypeQuery());
        return ResultData.success(sysDictTypeList);
    }
}
