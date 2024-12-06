package com.ruoyi.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysDictDataModifyBo;
import com.ruoyi.system.domain.bo.SysDictDataSaveBo;
import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;
import com.ruoyi.system.domain.vo.export.SysDictDataExportVo;
import com.ruoyi.system.service.SysDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.stream.Collectors;

/**
 * $SysDictTypeController
 *
 * @author Link
 */
@Tag(name = "字典数据管理", description = "字典数据管理")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController {
    
    private final SysDictDataService sysDictDataService;
    
    public SysDictDataController(SysDictDataService sysDictDataService) {
        this.sysDictDataService = sysDictDataService;
    }
    
    /**
     * 根据字典类型查询字典数据信息
     */
    @Parameters(value = {@Parameter(name = "dictType", description = "字典类型ID", in = ParameterIn.PATH)})
    @Operation(summary = "根据字典类型查询字典数据信息", description = "根据字典类型查询字典数据信息")
    @GetMapping(value = "/type/{dictType}")
    public Result<List<SysDictDataVo>> dictType(@PathVariable("dictType") String dictType) {
        List<SysDictDataVo> data = sysDictDataService.listDictDateByDictType(dictType);
        return ResultData.success(data);
    }
    
    @Operation(summary = "分页", description = "分页")
    @PostMapping(value = "/page")
    public Result<PageLight<SysDictDataVo>> page(@RequestBody SysDictDataQuery param) {
        return ResultData.success(sysDictDataService.page(param));
    }
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysDictDataSaveBo param) {
        sysDictDataService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {
            @Parameter(name = "dictDataCodes", description = "字典数据id数组", in = ParameterIn.PATH, example = "1,2")})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{dictDataCodes}")
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("dictDataCodes") Long[] dictDataCodes) {
        sysDictDataService.delete(dictDataCodes);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysDictDataModifyBo param) {
        sysDictDataService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "dictDataCode", description = "字典数据编码", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{dictDataCode}")
    public Result<SysDictDataVo> get(@PathVariable("dictDataCode") Long dictDataCode) {
        return ResultData.success(sysDictDataService.get(dictDataCode));
    }
    
    @Operation(summary = "导出", description = "导出")
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody SysDictDataQuery param, HttpServletResponse response) {
        List<SysDictDataExportVo> list = sysDictDataService.list(param).stream()
                .map(v -> BeanUtil.copyProperties(v, SysDictDataExportVo.class)).collect(Collectors.toList());
        ExcelUtil.exportExcel(list, "字典数据", SysDictDataExportVo.class, response);
    }
}
