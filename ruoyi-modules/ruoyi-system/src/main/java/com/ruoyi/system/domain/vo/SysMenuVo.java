package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysMenu;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Link
 * @date 2024-11-08
 */
@AutoMapper(target = SysMenu.class)
@Schema(description = "菜单权限表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVo extends SysMenu {
    
    /**
     * 子菜单
     */
    private List<SysMenuVo> children = new ArrayList<>();
}
