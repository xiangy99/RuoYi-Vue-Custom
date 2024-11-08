package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.bo.SysMenuModifyBO;
import com.ruoyi.system.domain.bo.SysMenuSaveBO;
import com.ruoyi.system.domain.pojo.SysMenu;
import com.ruoyi.system.domain.query.SysMenuQuery;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.domain.vo.SysMenuVo;

import java.util.List;

/**
 * @author Link
 */
public interface SysMenuService extends IService<SysMenu> {
    
    void save(SysMenuSaveBO param);
    
    void delete(Long menuId);
    
    void modify(SysMenuModifyBO param);
    
    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuVo> listTreeByUserId(Long userId);
    
    /**
     * 构建前端路由所需要的菜单
     *
     * @param param 菜单列表
     * @return 路由列表
     */
    List<RouterVo> build(List<SysMenuVo> param);
    
    /**
     * 根据用户查询系统菜单列表
     *
     * @param param  菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuVo> list(SysMenuQuery param, Long userId);
    
    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenuVo get(Long menuId);
    
    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName     菜单信息
     * @param menuId       菜单ID
     * @param parentMenuId 菜单父级ID
     * @return 结果
     */
    Boolean checkUniqueName(String menuName, Long menuId, Long parentMenuId);
    
    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Long> listMenuByRoleId(Long roleId);
}
