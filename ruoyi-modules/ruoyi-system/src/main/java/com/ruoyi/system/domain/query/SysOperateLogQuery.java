package com.ruoyi.system.domain.query;

import com.ruoyi.common.mybatis.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * $SysOperateLogQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysOperateLogQuery extends BasePageQuery implements Serializable {
    
    /**
     * 主机地址
     */
    @Schema(description = "主机地址")
    private String ip;
    
    /**
     * 模块标题
     */
    @Schema(description = "模块标题")
    private String title;
    
    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;
    
    /**
     * 操作状态（0成功 1失败）
     */
    @Schema(description = "操作状态（0成功 1失败）")
    @Size(max = 1, message = "操作状态（0成功 1失败）最大长度要小于 1")
    private String operateStatus;
    
    /**
     * 操作人员
     */
    @Schema(description = "操作人员")
    @Size(max = 50, message = "操作人员最大长度要小于 50")
    private String operatorName;
    
    @Builder.Default
    private Map<String, Object> baseQueryMap = new HashMap<>();
}
