package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysUser;
import com.ruoyi.system.domain.query.SysUserQuery;
import com.ruoyi.system.domain.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    
    List<SysUserVo> listAll(@Param("param") SysUserQuery param);
    
    Page<SysUserVo> page(@Param("param") SysUserQuery param, Page<SysUserQuery> page);
    
    SysUserVo getInfoWithRoleAndPost(@Param("userId") Long userId);
    
    SysUserVo getByUserName(@Param("userName") String userName);
    
    /**
     * 查询角色的已分配用户列表
     */
    Page<SysUserVo> listAllocatedUser(@Param("param") SysUserQuery param, Page<SysUserQuery> page);
    
    /**
     * 查询角色的未分配用户列表
     */
    Page<SysUserVo> listUnAllocatedUser(@Param("param") SysUserQuery param, Page<SysUserQuery> page);
    
}