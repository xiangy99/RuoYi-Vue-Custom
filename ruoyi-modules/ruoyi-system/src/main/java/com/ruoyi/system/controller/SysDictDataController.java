package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysDictDataModifyBo;
import com.ruoyi.system.domain.bo.SysDictDataSaveBo;
import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;
import com.ruoyi.system.service.SysDictDataService;
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
 * $SysDictTypeController
 *
 * @author Link
 */
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
    @GetMapping(value = "/type/{dictType}")
    public Result<List<SysDictDataVo>> dictType(@PathVariable("dictType") String dictType) {
        List<SysDictDataVo> data = sysDictDataService.listDictDateByDictType(dictType);
        return ResultData.success(data);
    }
    
    @PostMapping(value = "/page")
    public Result<PageLight<SysDictDataVo>> page(@RequestBody SysDictDataQuery param) {
        return ResultData.success(sysDictDataService.page(param));
    }
    
    @PostMapping
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysDictDataSaveBo param) {
        sysDictDataService.save(param);
        return ResultData.success();
    }
    
    @DeleteMapping("/{dictDataCodes}")
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("dictDataCodes") Long[] dictDataCodes) {
        sysDictDataService.delete(dictDataCodes);
        return ResultData.success();
    }
    
    @PutMapping
    @Log(title = "字典数据管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysDictDataModifyBo param) {
        sysDictDataService.modify(param);
        return ResultData.success();
    }
    
    @GetMapping("/{dictDataCode}")
    public Result<SysDictDataVo> get(@PathVariable("dictDataCode") Long dictDataCode) {
        return ResultData.success(sysDictDataService.get(dictDataCode));
    }
}
