package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "菜单权限表 Vo")
@Getter
@Setter
public class SysMenuVo extends SysMenu {
    
    /**
     * 子菜单
     */
    private List<SysMenuVo> children = new ArrayList<>();
}
