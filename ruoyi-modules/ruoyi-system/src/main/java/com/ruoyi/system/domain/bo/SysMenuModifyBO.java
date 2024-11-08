package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * $SysMenuModifyBO
 *
 * @author coriander
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuModifyBO implements Serializable {
    
    private static final long serialVersionUID = -1896334737075535573L;
    
    @Schema(description = "菜单ID")
    @NotNull(message = "菜单ID不能为null")
    private Long menuId;
    
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @Size(max = 50, message = "菜单名称最大长度要小于 50")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;
    
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;
    
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    @Size(max = 200, message = "路由地址最大长度要小于 200")
    private String path;
    
    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    @Size(max = 255, message = "组件路径最大长度要小于 255")
    private String component;
    
    /**
     * 路由参数
     */
    @Schema(description = "路由参数")
    @Size(max = 255, message = "路由参数最大长度要小于 255")
    private String query;
    
    /**
     * 是否为外链
     */
    @Schema(description = "是否为外链")
    private Boolean isFrame;
    
    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存")
    private Boolean isCache;
    
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @Schema(description = "菜单类型（M目录 C菜单 F按钮）")
    @Size(max = 1, message = "菜单类型（M目录 C菜单 F按钮）最大长度要小于 1")
    private String menuType;
    
    /**
     * 菜单状态（0显示 1隐藏）
     */
    @Schema(description = "菜单状态（0显示 1隐藏）")
    @Size(max = 1, message = "菜单状态（0显示 1隐藏）最大长度要小于 1")
    private String visible;
    
    /**
     * 菜单状态（0正常 1停用）
     */
    @Schema(description = "菜单状态（0正常 1停用）")
    @Size(max = 1, message = "菜单状态（0正常 1停用）最大长度要小于 1")
    private String status;
    
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    @Size(max = 100, message = "权限标识最大长度要小于 100")
    private String perms;
    
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @Size(max = 100, message = "菜单图标最大长度要小于 100")
    private String icon;
}
