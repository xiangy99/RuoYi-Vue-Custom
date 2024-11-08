package com.ruoyi.common.mybatis.domain;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author coriander
 */
@Getter
@Setter
public class PageDomain implements Serializable {
    
    private static final long serialVersionUID = 5661529086647172372L;
    
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;
    
    /**
     * 每页显示记录数
     */
    private Integer pageSize;
    
    /**
     * 排序
     */
    private List<OrderItem> orderList;
}
