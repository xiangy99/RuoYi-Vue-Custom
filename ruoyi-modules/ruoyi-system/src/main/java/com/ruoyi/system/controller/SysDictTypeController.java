package com.ruoyi.system.controller;

import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.query.SysDictTypeQuery;
import com.ruoyi.system.domain.vo.SysDictTypeVo;
import com.ruoyi.system.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @Operation(summary = "分页", description = "分页")
    @GetMapping("/page")
    public Result<PageLight<SysDictTypeVo>> page(SysDictTypeQuery param) {
        return ResultData.success(sysDictTypeService.page(param));
    }
}
