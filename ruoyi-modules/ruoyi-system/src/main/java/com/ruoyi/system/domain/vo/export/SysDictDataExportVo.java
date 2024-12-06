package com.ruoyi.system.domain.vo.export;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.annotation.ExcelEnumFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import com.ruoyi.common.excel.convert.ExcelEnumConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Link
 * @date 2024-12-06
 */
@ExcelIgnoreUnannotated
@Getter
@Setter
public class SysDictDataExportVo extends SysExportBaseVo implements Serializable {
    
    /**
     * 字典编码
     */
    @ExcelProperty(value = "字典编码")
    @Schema(description = "字典编码")
    private Long dictCode;
    
    /**
     * 字典标签
     */
    @ExcelProperty(value = "字典标签")
    @Schema(description = "字典标签")
    private String dictLabel;
    
    /**
     * 字典键值
     */
    @ExcelProperty(value = "字典键值")
    @Schema(description = "字典键值")
    private String dictValue;
    
    /**
     * 字典类型
     */
    @ExcelProperty(value = "字典类型")
    @Schema(description = "字典类型")
    private String dictType;
    
    /**
     * 样式属性（其他样式扩展）
     */
    @ExcelProperty(value = "样式属性（其他样式扩展）")
    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;
    
    /**
     * 表格回显样式
     */
    @ExcelProperty(value = "表格回显样式")
    @Schema(description = "表格回显样式")
    private String listClass;
    
    /**
     * 是否默认
     */
    @ExcelProperty(value = "是否默认", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    /**
     * 字典排序
     */
    @ExcelProperty(value = "字典排序")
    @Schema(description = "字典排序")
    private Integer sort;
    
    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = EnableStatusEnum.class, textField = "value")
    @Schema(description = "状态（0正常 1停用）")
    private String status;
}
