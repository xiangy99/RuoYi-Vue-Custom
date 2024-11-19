package com.ruoyi.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */

/**
 * 参数配置表
 */
@Schema(description = "参数配置表")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig extends BaseEntity implements Serializable {
    
    /**
     * 参数主键
     */
    @TableId
    @Schema(description = "参数主键")
    @NotNull(message = "参数主键不能为null")
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
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
}