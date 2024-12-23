package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.pojo.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    
    int deleteByRoleId(@Param("roleId") Long roleId);
}