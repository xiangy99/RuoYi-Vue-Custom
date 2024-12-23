package com.ruoyi.system.service;

import com.ruoyi.common.core.vo.TreeNode;
import com.ruoyi.system.domain.bo.SysDeptModifyBo;
import com.ruoyi.system.domain.bo.SysDeptSaveBo;
import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.domain.vo.SysDeptVo;

import java.util.List;

/**
 * $SysDeptService
 *
 * @author Link
 */
public interface SysDeptService {
    
    void save(SysDeptSaveBo param);
    
    void delete(Long deptId);
    
    void modify(SysDeptModifyBo param);
    
    SysDeptVo get(Long deptId);
    
    List<SysDeptVo> list(SysDeptQuery param);
    
    Boolean checkUniqueName(String deptName, Long deptId, Long parentDeptId);
    
    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    List<Long> listDeptByRoleId(Long roleId);
    
    List<TreeNode> listDeptTree(SysDeptQuery param);
    
}
