package com.ruoyi.system.domain.query;

import com.ruoyi.common.mybatis.domain.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * $SysUserQuery
 *
 * @author Link
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserQuery extends BasePageQuery {
    
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickName;
    
    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phonenumber;
    
    /**
     * 帐号状态（0正常 1停用）
     */
    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;
    
    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 登陆账号
     */
    private String userName;
    
    @Builder.Default
    private Map<String, Object> baseQueryMap = new HashMap<>();
}
