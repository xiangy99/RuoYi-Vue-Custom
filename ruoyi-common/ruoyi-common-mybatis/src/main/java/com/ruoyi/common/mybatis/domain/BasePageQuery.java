package com.ruoyi.common.mybatis.domain;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Link
 * @date 2024-11-21
 */
@Getter
@Setter
public class BasePageQuery implements Serializable {
    
    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;
    
    @Schema(description = "每页条数", example = "10")
    private Integer pageSize = 10;
    
    private List<OrderItem> orderList;
    
}
