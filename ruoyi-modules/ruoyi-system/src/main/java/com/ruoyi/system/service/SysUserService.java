package com.ruoyi.system.service;

import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysUserModifyBo;
import com.ruoyi.system.domain.bo.SysUserSaveBo;
import com.ruoyi.system.domain.query.SysUserQuery;
import com.ruoyi.system.domain.vo.SysUserVo;

/**
 * $SysUserService
 *
 * @author Link
 */
public interface SysUserService {
    
    void save(SysUserSaveBo param);
    
    void delete(Long userId);
    
    void modify(SysUserModifyBo param);
    
    SysUserVo get(Long userId);
    
    PageLight<SysUserVo> page(SysUserQuery param);
    
    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户基本信息、用户所属角色信息、用户所属岗位信息
     */
    SysUserVo getInfoWithRoleAndPost(Long userId);
    
    /**
     * 修改用户状态
     *
     * @param userId 用户ID
     * @param status 用户状态
     */
    void updateStatus(Long userId, String status);
    
    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID数组
     */
    void authRole(Long userId, Long[] roleIds);
    
    SysUserVo getByUserName(String userName);
    
    /**
     * 查询角色的已分配用户列表
     *
     * @param param 查询参数
     * @return 返回结果
     */
    PageLight<SysUserVo> listAllocatedUser(SysUserQuery param);
    
    /**
     * 查询角色的未分配用户列表
     *
     * @param param 查询参数
     * @return 返回结果
     */
    PageLight<SysUserVo> listUnAllocatedUser(SysUserQuery param);
}
