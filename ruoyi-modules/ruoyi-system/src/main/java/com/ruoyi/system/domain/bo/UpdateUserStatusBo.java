package com.ruoyi.system.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * $UpdateStatusBO
 *
 * @author coriander
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserStatusBo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -2440734367970304329L;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户状态
     */
    private String status;
}
