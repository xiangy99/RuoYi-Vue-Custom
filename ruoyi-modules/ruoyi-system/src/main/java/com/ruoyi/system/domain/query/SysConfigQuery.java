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
 * $SysConfigQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysConfigQuery extends BasePageQuery implements Serializable {
    
    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    @Size(max = 100, message = "参数名称最大长度要小于 100")
    private String configName;
    
    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    @Size(max = 100, message = "参数键名最大长度要小于 100")
    private String configKey;
    
    /**
     * 是否默认
     */
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    @Builder.Default
    private Map<String, Object> baseQueryMap = new HashMap<>();
}
