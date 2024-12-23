package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.core.enums.YesOrNoEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.system.domain.bo.SysRoleModifyBo;
import com.ruoyi.system.domain.bo.SysRoleSaveBo;
import com.ruoyi.system.domain.pojo.SysRole;
import com.ruoyi.system.domain.pojo.SysRoleDept;
import com.ruoyi.system.domain.pojo.SysRoleMenu;
import com.ruoyi.system.domain.pojo.SysUserRole;
import com.ruoyi.system.domain.query.SysRoleQuery;
import com.ruoyi.system.domain.vo.SysRoleVo;
import com.ruoyi.system.mapper.SysRoleDeptMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysRoleMenuMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * $SysRoleServiceImpl
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    
    private final SysRoleMapper sysRoleMapper;
    
    private final SysRoleMenuMapper sysRoleMenuMapper;
    
    private final SysRoleDeptMapper sysRoleDeptMapper;
    
    private final SysUserRoleMapper sysUserRoleMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleSaveBo param) {
        ValidatorUtil.validate(param);
        
        // 校验角色名称是否存在
        if (!this.checkUniqueName(param.getRoleName(), param.getRoleId())) {
            throw new BusinessException(ResultCode.Business.ROLE_NAME_IS_NOT_UNIQUE);
        }
        
        // 校验角色权限是否存在
        if (!this.checkUniqueKey(param.getRoleKey(), param.getRoleId())) {
            throw new BusinessException(ResultCode.Business.ROLE_KEY_IS_NOT_UNIQUE);
        }
        
        SysRole sysRoleRecord = BeanUtil.copyProperties(param, SysRole.class);
        sysRoleRecord.setRoleId(IdUtil.getId());
        sysRoleRecord.setStatus(EnableStatusEnum.ENABLE.getCode());
        sysRoleRecord.setCreateTime(LocalDateTime.now());
        sysRoleRecord.setUpdateTime(LocalDateTime.now());
        
        int i = sysRoleMapper.insert(sysRoleRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.ROLE_SAVE_FAIL);
        }
        
        // 保存角色和菜单的关系
        this.handleSaveSysRoleMenu(sysRoleRecord.getRoleId(), param.getMenuIdList());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long[] roleIds) {
        ValidatorUtil.validate(roleIds);
        
        for (Long roleId : roleIds) {
            // 校验角色是否允许操作
            
            // 校验角色是否有数据权限
            
            // 校验角色是否已经分配
            
            SysRole sysRoleRecord = new SysRole();
            sysRoleRecord.setRoleId(roleId);
            sysRoleRecord.setIsDeleted(YesOrNoEnum.YES.getCode());
            sysRoleRecord.setUpdateTime(LocalDateTime.now());
            
            int i = sysRoleMapper.updateById(sysRoleRecord);
            if (i != 1) {
                throw new BusinessException(ResultCode.Business.ROLE_DELETE_FAIL);
            }
            
            // 删除角色和菜单关联
            sysRoleMenuMapper.deleteByRoleId(roleId);
            
            // TODO 删除角色与部门关联
        }
        
        
    }
    
    @Override
    public void modify(SysRoleModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 校验角色名称是否存在
        if (!this.checkUniqueName(param.getRoleName(), param.getRoleId())) {
            throw new BusinessException(ResultCode.Business.ROLE_NAME_IS_NOT_UNIQUE);
        }
        
        // 校验角色权限是否存在
        if (!this.checkUniqueKey(param.getRoleKey(), param.getRoleId())) {
            throw new BusinessException(ResultCode.Business.ROLE_KEY_IS_NOT_UNIQUE);
        }
        
        SysRole sysRoleRecord = BeanUtil.copyProperties(param, SysRole.class);
        sysRoleRecord.setUpdateTime(LocalDateTime.now());
        int i = sysRoleMapper.updateById(sysRoleRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.ROLE_MODIFY_FAIL);
        }
        
        // 删除角色和菜单关联
        sysRoleMapper.deleteById(param.getRoleId());
        
        // 保存角色和菜单的关系
        this.handleSaveSysRoleMenu(sysRoleRecord.getRoleId(), param.getMenuIdList());
    }
    
    @Override
    public SysRoleVo get(Long roleId) {
        ValidatorUtil.validate(roleId);
        
        SysRole sysRoleInfo = sysRoleMapper.selectById(roleId);
        SysRoleVo vo = BeanUtil.copyProperties(sysRoleInfo, SysRoleVo.class);
        
        return vo;
    }
    
    @Override
    public List<SysRoleVo> list(SysRoleQuery param) {
        List<SysRoleVo> sysRoleList = sysRoleMapper.listAll(param);
        
        return sysRoleList;
    }
    
    @Override
    public PageLight<SysRoleVo> page(SysRoleQuery param) {
        Page<SysRoleVo> sysRoleList = sysRoleMapper.page(param, PageUtil.getPage(param));
        return new PageLight<>(sysRoleList);
    }
    
    @Override
    public Boolean checkUniqueKey(String roleKey, Long roleId) {
        ValidatorUtil.validate(roleKey);
        
        roleId = roleId == null ? -1L : roleId;
        SysRoleVo sysRoleInfo = sysRoleMapper.getByRoleKey(roleKey);
        if (sysRoleInfo != null && !roleId.equals(sysRoleInfo.getRoleId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @Override
    public Boolean checkUniqueName(String roleName, Long roleId) {
        ValidatorUtil.validate(roleName);
        roleId = roleId == null ? -1L : roleId;
        
        SysRoleVo sysRoleInfo = sysRoleMapper.getByRoleName(roleName);
        if (sysRoleInfo != null && !roleId.equals(sysRoleInfo.getRoleId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @Override
    public ResultData updateStatus(Long roleId, String status) {
        ValidatorUtil.validate(roleId, status);
        
        // 校验角色是否允许操作
        
        // 校验用户角色是否有数据权限操作
        
        sysRoleMapper.updateStatusByRoleId(roleId, status);
        return ResultData.success();
    }
    
    @Override
    public void updateDataScope(SysRoleModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 校验角色是否允许操作
        
        // 校验角色是否有数据权限
        
        // 修改角色信息
        SysRole sysRoleRecord = BeanUtil.copyProperties(param, SysRole.class);
        sysRoleRecord.setUpdateTime(LocalDateTime.now());
        sysRoleMapper.updateById(sysRoleRecord);
        
        // 删除角色与部门关联
        sysRoleDeptMapper.deleteByRoleId(param.getRoleId());
        
        // 新增角色与部门关联
        if (ArrayUtil.isNotEmpty(param.getDeptIdList())) {
            List<SysRoleDept> sysRoleDeptList = new ArrayList<>();
            for (Long deptId : param.getDeptIdList()) {
                SysRoleDept sysRoleDept = new SysRoleDept();
                sysRoleDept.setRoleId(param.getRoleId());
                sysRoleDept.setDeptId(deptId);
            }
            if (CollUtil.isNotEmpty(sysRoleDeptList)) {
                sysRoleDeptMapper.insert(sysRoleDeptList);
            }
        }
        
        
    }
    
    @Override
    public List<SysRoleVo> listRoleByUserId(Long userId) {
        ValidatorUtil.validate(userId);
        
        // 用户所属角色列表
        List<SysRoleVo> userRoleList = sysRoleMapper.listRoleByUserId(userId);
        // 所有角色列表
        List<SysRoleVo> allRoleList = sysRoleMapper.listAll(new SysRoleQuery());
        
        for (SysRoleVo roleVo : allRoleList) {
            for (SysRoleVo userRole : userRoleList) {
                if (roleVo.getRoleId().equals(userRole.getRoleId())) {
                    roleVo.setFlag(true);
                    break;
                }
            }
        }
        
        return allRoleList;
    }
    
    @Override
    public void batchAuthUser(List<Long> userIdList, Long roleId) {
        ValidatorUtil.validate(userIdList, roleId);
        
        // TODO 校验角色是否有数据权限
        
        List<SysUserRole> recordList = new ArrayList<>();
        for (Long userId : userIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            recordList.add(sysUserRole);
        }
        sysUserRoleMapper.insertList(recordList);
    }
    
    @Override
    public void cancelAuthUser(Long userId, Long roleId) {
        ValidatorUtil.validate(userId, roleId);
        sysUserRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
        
    }
    
    @Override
    public void batchCancelAuthUser(List<Long> userIdList, Long roleId) {
        ValidatorUtil.validate(userIdList, roleId);
        for (Long userId : userIdList) {
            sysUserRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
        }
    }
    
    /**
     * 新增角色菜单信息
     *
     * @param roleId     角色ID
     * @param menuIdList 菜单ID列表
     */
    public void handleSaveSysRoleMenu(Long roleId, List<Long> menuIdList) {
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : menuIdList) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (CollUtil.isNotEmpty(list)) {
            sysRoleMenuMapper.insert(list);
        }
    }
}
