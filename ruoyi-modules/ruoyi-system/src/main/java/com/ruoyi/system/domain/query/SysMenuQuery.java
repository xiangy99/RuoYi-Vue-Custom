package com.ruoyi.system.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * $SysMenuQuery
 *
 * @author coriander
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuQuery {
    
    private String menuName;
    
    private String status;
}
