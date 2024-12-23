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
@RestController
@RequestMapping("/system/dept")
public class SysDeptController {
    
    private final SysDeptService sysDeptService;
    
    public SysDeptController(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }
    
    @PostMapping
    @Log(title = "部门管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysDeptSaveBo param) {
        sysDeptService.save(param);
        return ResultData.success();
    }
    
    @DeleteMapping("/{deptId}")
    @Log(title = "部门管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("deptId") Long deptId) {
        sysDeptService.delete(deptId);
        return ResultData.success();
    }
    
    @PutMapping
    @Log(title = "部门管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysDeptModifyBo param) {
        sysDeptService.modify(param);
        return ResultData.success();
    }
    
    @GetMapping("/{deptId}")
    public Result<SysDeptVo> get(@PathVariable("deptId") Long deptId) {
        return ResultData.success(sysDeptService.get(deptId));
    }
    
    @GetMapping("/list")
    public Result<List<SysDeptVo>> list(SysDeptQuery param) {
        return ResultData.success(sysDeptService.list(param));
    }
    
    /**
     * 查询部门列表（排除节点）
     */
    @GetMapping("/list/exclude/{deptId}")
    public ResultData excludeChild(@PathVariable("deptId") Long deptId) {
        List<SysDeptVo> list = sysDeptService.list(new SysDeptQuery());
        list.removeIf(
                v -> v.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(v.getAncestors(), ","),
                        deptId + ""));
        return ResultData.success(list);
    }
}
