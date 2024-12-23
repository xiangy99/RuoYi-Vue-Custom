package com.ruoyi.system.service;

import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysRoleModifyBo;
import com.ruoyi.system.domain.bo.SysRoleSaveBo;
import com.ruoyi.system.domain.query.SysRoleQuery;
import com.ruoyi.system.domain.vo.SysRoleVo;

import java.util.List;

/**
 * $SysRoleService
 *
 * @author Link
 */
public interface SysRoleService {
    
    void save(SysRoleSaveBo param);
    
    void delete(Long[] roleIds);
    
    void modify(SysRoleModifyBo param);
    
    SysRoleVo get(Long roleId);
    
    List<SysRoleVo> list(SysRoleQuery param);
    
    PageLight<SysRoleVo> page(SysRoleQuery param);
    
    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @param roleId  角色ID
     * @return 结果
     */
    Boolean checkUniqueKey(String roleKey, Long roleId);
    
    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @param roleId   角色ID
     */
    Boolean checkUniqueName(String roleName, Long roleId);
    
    /**
     * 修改角色状态
     *
     * @param roleId 角色ID
     * @param status 状态
     * @return 结果
     */
    ResultData updateStatus(Long roleId, String status);
    
    /**
     * 修改数据权限信息
     *
     * @param param 角色信息
     */
    void updateDataScope(SysRoleModifyBo param);
    
    /**
     * 根据用户ID查询角色信息(返回所有角色ID列表,flag=true，证明用户存在该角色)
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRoleVo> listRoleByUserId(Long userId);
    
    /**
     * 批量授权用户角色
     */
    void batchAuthUser(List<Long> userIdList, Long roleId);
    
    /**
     * 取消授权用户角色
     */
    void cancelAuthUser(Long userId, Long roleId);
    
    /**
     * 批量取消授权用户角色
     */
    void batchCancelAuthUser(List<Long> userIdList, Long roleId);
    
}
