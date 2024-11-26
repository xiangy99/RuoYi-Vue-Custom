package com.ruoyi.common.core.enums;

import lombok.Getter;

/**
 * 对应字典：sys_enable_status 0正常1停用
 *
 * @author coriander
 */
@Getter
public enum EnableStatusEnum {
    
    ENABLE("0", "正常"),
    
    DISABLE("1", "停用");
    
    private final String code;
    
    private final String value;
    
    EnableStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
