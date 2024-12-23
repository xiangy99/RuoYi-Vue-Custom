package com.ruoyi.system.domain.query;

import com.ruoyi.common.mybatis.domain.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysPostQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysPostQuery extends BasePageQuery implements Serializable {
    
    /**
     * 岗位名称
     */
    private String postName;
    
    /**
     * 岗位编码
     */
    private String postCode;
    
    /**
     * 状态
     */
    private String status;
}
