package com.ruoyi.system.domain.vo.export;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.enums.YesOrNoEnum;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.annotation.ExcelEnumFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import com.ruoyi.common.excel.convert.ExcelEnumConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 字典类型导出返回结果
 *
 * @author Link
 * @date 2024-12-06
 */
@ExcelIgnoreUnannotated
@Getter
@Setter
public class SysDictTypeExportVo extends SysExportBaseVo implements Serializable {
    
    /**
     * 字典主键
     */
    @ExcelProperty(value = "字典主键")
    @Schema(description = "字典主键")
    private Long dictId;
    
    /**
     * 字典名称
     */
    @ExcelProperty(value = "字典名称")
    @Schema(description = "字典名称")
    private String dictName;
    
    /**
     * 字典类型
     */
    @ExcelProperty(value = "字典类型")
    @Schema(description = "字典类型")
    private String dictType;
    
    /**
     * 是否默认
     */
    @ExcelProperty(value = "是否默认", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = YesOrNoEnum.class, textField = "value")
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    /**
     * 状态（0正常 1停用）
     */
    //    @ExcelProperty(value = "状态", converter = ExcelEnumConvert.class)
    //    @ExcelEnumFormat(enumClass = EnableStatusEnum.class, textField = "value")
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_enable_status")
    @Schema(description = "状态（0正常 1停用）")
    private String status;
}
