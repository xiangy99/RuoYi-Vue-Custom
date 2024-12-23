package com.ruoyi.system.domain.query;

import com.ruoyi.common.mybatis.domain.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * $SysRoleQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleQuery extends BasePageQuery {
    
    private String roleName;
    
    private String status;
    
    private String roleKey;
    
    private Long roleId;
    
    @Builder.Default
    private Map<String, Object> baseQueryMap = new HashMap<>();
    
}
