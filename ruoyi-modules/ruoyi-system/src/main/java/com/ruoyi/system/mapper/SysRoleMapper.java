package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysRole;
import com.ruoyi.system.domain.query.SysRoleQuery;
import com.ruoyi.system.domain.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    
    SysRoleVo getByRoleKey(@Param("roleKey") String roleKey);
    
    SysRoleVo getByRoleName(@Param("roleName") String roleName);
    
    List<SysRoleVo> listAll(SysRoleQuery param);
    
    Page<SysRoleVo> page(@Param("param") SysRoleQuery param, Page<SysRoleQuery> page);
    
    int updateStatusByRoleId(@Param("roleId") Long roleId, @Param("updatedStatus") String updatedStatus);
    
    List<SysRoleVo> listRoleByUserId(@Param("userId") Long userId);
    
}