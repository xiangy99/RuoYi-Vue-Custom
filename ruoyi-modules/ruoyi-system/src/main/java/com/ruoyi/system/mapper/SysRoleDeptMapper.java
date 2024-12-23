package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.pojo.SysRoleDept;
import org.apache.ibatis.annotations.Param;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {
    
    int deleteByRoleId(@Param("roleId") Long roleId);
    
}