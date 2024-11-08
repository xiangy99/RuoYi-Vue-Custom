package com.ruoyi.common.core.enums;

import lombok.Getter;

/**
 * 对应字典：sys_yes_no 1是0否 $YesOrNoEnum
 *
 * @author Link
 */
@Getter
public enum YesOrNoEnum {
    YES(Boolean.TRUE, "是"),
    NO(Boolean.FALSE, "否");
    
    private final Boolean code;
    
    private final String value;
    
    YesOrNoEnum(Boolean code, String value) {
        this.code = code;
        this.value = value;
    }
}

