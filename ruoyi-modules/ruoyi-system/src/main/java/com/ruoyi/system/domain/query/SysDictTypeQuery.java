package com.ruoyi.system.domain.query;

import com.ruoyi.common.mybatis.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * $SysDictTypeQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDictTypeQuery extends BasePageQuery implements Serializable {
    
    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;
    
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;
    
    /**
     * 状态（0正常 1停用）
     */
    @Schema(description = "状态（0正常 1停用）")
    private String status;
    
    @Builder.Default
    private Map<String, Object> baseQueryMap = new HashMap<>();
    
}
