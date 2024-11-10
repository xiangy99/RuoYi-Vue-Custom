package com.ruoyi.system.domain.bo;


import lombok.*;

/**
 * $SysLoginBo
 *
 * @author coriander
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLoginBo {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
