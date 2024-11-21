package com.ruoyi.common.core.enums;

import lombok.Getter;

/**
 *对应字典：sys_common_status 1失败0成功
 * @author coriander
 */
@Getter
public enum CommonStatusEnum {

    FAIL("1", "失败"),

    SUCCESS("0", "成功");

    private final String code;
    private final String value;

    CommonStatusEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
