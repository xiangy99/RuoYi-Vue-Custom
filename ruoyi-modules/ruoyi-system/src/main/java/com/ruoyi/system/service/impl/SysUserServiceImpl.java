package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.core.enums.YesOrNoEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.system.domain.bo.SysUserModifyBo;
import com.ruoyi.system.domain.bo.SysUserSaveBo;
import com.ruoyi.system.domain.pojo.SysUser;
import com.ruoyi.system.domain.pojo.SysUserPost;
import com.ruoyi.system.domain.pojo.SysUserRole;
import com.ruoyi.system.domain.query.SysUserQuery;
import com.ruoyi.system.domain.vo.SysDeptVo;
import com.ruoyi.system.domain.vo.SysUserVo;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * $SysUserServiceImpl
 *
 * @author Link
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    
    private final SysUserMapper sysUserMapper;
    
    private final SysUserRoleMapper sysUserRoleMapper;
    
    private final SysUserPostMapper sysUserPostMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysUserSaveBo param) {
        ValidatorUtil.validate(param);
        
        SysUser sysUserRecord = BeanUtil.copyProperties(param, SysUser.class);
        sysUserRecord.setUserId(IdUtil.getId());
        sysUserRecord.setStatus(EnableStatusEnum.ENABLE.getCode());
        sysUserRecord.setIsDeleted(YesOrNoEnum.NO.getCode());
        sysUserRecord.setCreateTime(LocalDateTime.now());
        sysUserRecord.setUpdateTime(LocalDateTime.now());
        sysUserRecord.setPassword(SecureUtil.sha256(param.getPassword() + Constants.DEFAULT_SALT));
        int i = sysUserMapper.insert(sysUserRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.USER_SAVE_FAIL);
        }
        
        // 保存用户与角色关系
        this.handleSaveUserRole(sysUserRecord.getUserId(), param.getRoleIdList());
        
        // 保存用户与岗位关系
        this.handleSaveUserPost(sysUserRecord.getUserId(), param.getPostIdList());
    }
    
    private void handleSaveUserPost(Long userId, List<Long> postIdList) {
        if (CollUtil.isEmpty(postIdList)) {
            return;
        }
        for (Long postId : postIdList) {
            SysUserPost record = SysUserPost.builder().userId(userId).postId(postId).build();
            sysUserPostMapper.insert(record);
        }
    }
    
    private void handleSaveUserRole(Long userId, List<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return;
        }
        for (Long roleId : roleIdList) {
            SysUserRole record = SysUserRole.builder().userId(userId).roleId(roleId).build();
            sysUserRoleMapper.insert(record);
        }
    }
    
    @Override
    public void delete(Long userId) {
        ValidatorUtil.validate(userId);
        
        SysUser sysUserRecord = new SysUser();
        sysUserRecord.setUserId(userId);
        sysUserRecord.setIsDeleted(YesOrNoEnum.YES.getCode());
        sysUserRecord.setUpdateTime(LocalDateTime.now());
        int i = sysUserMapper.updateById(sysUserRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.USER_DELETE_FAIL);
        }
    }
    
    @Override
    public void modify(SysUserModifyBo param) {
        ValidatorUtil.validate(param);
        
        SysUser sysUserRecord = BeanUtil.copyProperties(param, SysUser.class);
        sysUserRecord.setUpdateTime(LocalDateTime.now());
        int i = sysUserMapper.updateById(sysUserRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.USER_MODIFY_FAIL);
        }
        
        sysUserRoleMapper.deleteByUserId(param.getUserId());
        this.handleSaveUserRole(param.getUserId(), param.getRoleIdList());
        
        sysUserPostMapper.deleteByUserId(param.getUserId());
        this.handleSaveUserPost(param.getUserId(), param.getPostIdList());
    }
    
    @Override
    public SysUserVo get(Long userId) {
        ValidatorUtil.validate(userId);
        
        SysUser sysUserInfo = sysUserMapper.selectById(userId);
        SysUserVo vo = BeanUtil.copyProperties(sysUserInfo, SysUserVo.class);
        
        return vo;
    }
    
    @Override
    public PageLight<SysUserVo> page(SysUserQuery param) {
        Page<SysUserVo> sysUserList = sysUserMapper.page(param, PageUtil.getPage(param));
        PageLight<SysUserVo> pageLight = new PageLight<>(sysUserList);
        if (CollUtil.isEmpty(sysUserList.getRecords())) {
            return pageLight;
        }
        for (SysUserVo vo : sysUserList.getRecords()) {
            vo.setDeptName(StrUtil.join(",", vo.getDeptList().stream().map(SysDeptVo::getDeptName).toList()));
        }
        
        return pageLight;
    }
    
    @Override
    public SysUserVo getInfoWithRoleAndPost(Long userId) {
        ValidatorUtil.validate(userId);
        
        return sysUserMapper.getInfoWithRoleAndPost(userId);
    }
    
    @Override
    public void updateStatus(Long userId, String status) {
        ValidatorUtil.validate(userId, status);
        
        this.checkUserAllowed(userId);
        this.checkUserDataScope(userId);
        
        SysUser sysUserRecord = new SysUser();
        sysUserRecord.setUserId(userId);
        sysUserRecord.setStatus(status);
        sysUserRecord.setUpdateTime(LocalDateTime.now());
        int i = sysUserMapper.updateById(sysUserRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.USER_STATUS_UPDATE_FAIL);
        }
    }
    
    @Override
    public void authRole(Long userId, Long[] roleIds) {
        ValidatorUtil.validate(userId);
        
        // TODO 校验用户是否有数据权限
        
        // TODO 校验角色是否有数据权限
        
        sysUserRoleMapper.deleteByUserId(userId);
        
        if (ArrayUtil.isEmpty(roleIds)) {
            return;
        }
        
        for (Long roleId : roleIds) {
            SysUserRole record = SysUserRole.builder().userId(userId).roleId(roleId).build();
            sysUserRoleMapper.insert(record);
        }
    }
    
    /**
     * 检查用户是否允许操作
     *
     * @param userId 用户ID
     */
    private void checkUserAllowed(Long userId) {
        //        if (SecurityUtils.isAdmin(userId)) {
        //            throw new BusinessException(ResultCode.Business.UNSUPPORTED_OPERATION_ADMIN);
        //        }
    }
    
    /**
     * 检查用户数据权限
     *
     * @param userId 用户ID
     */
    private void checkUserDataScope(Long userId) {
        // TODO 校验用户是否有数据权限
    }
    
    @Override
    public SysUserVo getByUserName(String userName) {
        return sysUserMapper.getByUserName(userName);
    }
    
    @Override
    public PageLight<SysUserVo> listAllocatedUser(SysUserQuery param) {
        Page<SysUserVo> list = sysUserMapper.listAllocatedUser(param, PageUtil.getPage(param));
        return new PageLight<>(list);
    }
    
    @Override
    public PageLight<SysUserVo> listUnAllocatedUser(SysUserQuery param) {
        Page<SysUserVo> list = sysUserMapper.listUnAllocatedUser(param, PageUtil.getPage(param));
        return new PageLight<>(list);
    }
}
