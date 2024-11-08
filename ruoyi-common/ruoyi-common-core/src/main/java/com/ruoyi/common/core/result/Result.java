package com.ruoyi.common.core.result;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "返回结果")
    private T data;
}
