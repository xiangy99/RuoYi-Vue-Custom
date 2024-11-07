package com.ruoyi.common.core.result;

import lombok.Getter;
import lombok.Setter;

/**
 * $Result
 *
 * @author Link
 */
@Getter
@Setter
public class Result<T> extends ResultData {
    
    /**
     * 返回参数
     */
    private T data;
}
