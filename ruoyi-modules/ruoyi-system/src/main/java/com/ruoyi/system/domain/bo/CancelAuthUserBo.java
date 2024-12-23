package com.ruoyi.system.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * $CancelAuthUserBO
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelAuthUserBo implements Serializable {
    
    private Long userId;
    
    private List<Long> userIdList;
    
    private Long roleId;
}
