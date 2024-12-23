package com.ruoyi.system.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysDeptQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDeptQuery implements Serializable {
    
    private static final long serialVersionUID = -1591996186432307791L;
    
    /**
     * 部门名称
     */
    private String deptName;
    
    
    /**
     * 状态
     */
    private String status;
}
