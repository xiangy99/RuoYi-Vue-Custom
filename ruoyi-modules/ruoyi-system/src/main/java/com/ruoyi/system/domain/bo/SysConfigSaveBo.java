package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysConfigSaveBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysConfigSaveBo implements Serializable {
    
    /**
     * 参数主键
     */
    @Schema(description = "参数主键")
    private Long configId;
    
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
     * 参数键值
     */
    @Schema(description = "参数键值")
    @Size(max = 500, message = "参数键值最大长度要小于 500")
    private String configValue;
    
    /**
     * 是否默认
     */
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
