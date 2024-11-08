package com.ruoyi.system.mapper;

import com.ruoyi.common.mybatis.mapper.BaseMapperPlus;
import com.ruoyi.system.domain.pojo.SysMenu;
import com.ruoyi.system.domain.query.SysMenuQuery;
import com.ruoyi.system.domain.vo.SysMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysMenuMapper extends BaseMapperPlus<SysMenu, SysMenuVo> {
    
    List<SysMenuVo> listMenuTreeAll();
    
    List<SysMenuVo> listAll(@Param("param") SysMenuQuery param);
    
    SysMenuVo getByMenuNameAndParentId(@Param("menuName") String menuName, @Param("parentId") Long parentId);
    
    /**
     * 是否存在正在使用的子菜单
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int countChildByMenuId(@Param("menuId") Long menuId);
    
    /**
     * 查询菜单是否分配给角色
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int countRoleByMenuId(@Param("menuId") Long menuId);
    
    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId            角色ID
     * @param menuCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    List<Long> listMenuByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);
    
}