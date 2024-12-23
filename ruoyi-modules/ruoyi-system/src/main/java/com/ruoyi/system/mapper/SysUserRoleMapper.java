package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.pojo.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    
    int deleteByUserId(@Param("userId") Long userId);
    
    int insertList(@Param("list") List<SysUserRole> list);
    
    int deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
    
}