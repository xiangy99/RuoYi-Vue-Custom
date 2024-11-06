package com.ruoyi.common.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * $Result
 *
 * @author Link
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> extends ResultData {
    
    /**
     * 返回参数
     */
    private T data;
}
