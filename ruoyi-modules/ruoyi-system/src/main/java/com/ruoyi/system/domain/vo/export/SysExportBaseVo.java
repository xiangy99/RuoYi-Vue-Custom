package com.ruoyi.system.domain.vo.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Link
 * @date 2024-12-06
 */
@Getter
@Setter
public class SysExportBaseVo implements Serializable {
    
    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    @ColumnWidth(value = 25)
    @Schema(description = "创建者")
    private Long createBy;
    
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ColumnWidth(value = 25)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    @ColumnWidth(value = 25)
    @Schema(description = "更新者")
    private Long updateBy;
    
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    @ColumnWidth(value = 25)
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ColumnWidth(value = 50)
    @Schema(description = "备注")
    private String remark;
    
}
