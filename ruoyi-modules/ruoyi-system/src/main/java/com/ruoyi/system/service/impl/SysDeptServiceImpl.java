package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.core.enums.YesOrNoEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.core.vo.TreeNode;
import com.ruoyi.system.domain.bo.SysDeptModifyBo;
import com.ruoyi.system.domain.bo.SysDeptSaveBo;
import com.ruoyi.system.domain.pojo.SysDept;
import com.ruoyi.system.domain.pojo.SysRole;
import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.domain.vo.SysDeptVo;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * $SysDeptServiceImpl
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl implements SysDeptService {
    
    private final SysDeptMapper sysDeptMapper;
    
    private final SysRoleMapper sysRoleMapper;
    
    @Override
    public void save(SysDeptSaveBo param) {
        ValidatorUtil.validate(param);
        
        // 校验部门名称是否存在
        if (!this.checkUniqueName(param.getDeptName(), param.getDeptId(), param.getParentId())) {
            throw new BusinessException(ResultCode.Business.DEPT_NAME_IS_NOT_UNIQUE);
        }
        // 校验能否增加子部门
        SysDept parentSysDeptInfo = sysDeptMapper.selectById(param.getParentId());
        if (parentSysDeptInfo == null) {
            throw new BusinessException(ResultCode.Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED);
        }
        if (parentSysDeptInfo.getAncestors().split(",").length >= 4) {
            throw new BusinessException(ResultCode.Business.DEPT_ADD_CHILD_DEPT_OVER_LIMIT);
        }
        
        SysDept sysDeptRecord = BeanUtil.copyProperties(param, SysDept.class);
        sysDeptRecord.setDeptId(IdUtil.getId());
        sysDeptRecord.setStatus(param.getStatus() != null ? param.getStatus() : EnableStatusEnum.ENABLE.getCode());
        sysDeptRecord.setIsDeleted(YesOrNoEnum.NO.getCode());
        sysDeptRecord.setCreateTime(LocalDateTime.now());
        sysDeptRecord.setUpdateTime(LocalDateTime.now());
        
        // 设置ancestors
        if (parentSysDeptInfo.getIsDeleted() || !parentSysDeptInfo.getStatus()
                .equals(EnableStatusEnum.ENABLE.getCode())) {
            throw new BusinessException(ResultCode.Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED);
        }
        sysDeptRecord.setAncestors(parentSysDeptInfo.getAncestors() + "," + param.getParentId());
        
        int i = sysDeptMapper.insert(sysDeptRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DEPT_SAVE_FAIL);
        }
    }
    
    @Override
    public void delete(Long deptId) {
        ValidatorUtil.validate(deptId);
        
        // 校验是否存在的子部门
        if (sysDeptMapper.countChildByDeptId(deptId) > 0) {
            throw new BusinessException(ResultCode.Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE);
        }
        // 校验部门下是否还存在员工
        if (sysDeptMapper.countUserByDeptId(deptId) > 0) {
            throw new BusinessException(ResultCode.Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE);
        }
        
        SysDept sysDeptRecord = new SysDept();
        sysDeptRecord.setDeptId(deptId);
        sysDeptRecord.setIsDeleted(YesOrNoEnum.YES.getCode());
        sysDeptRecord.setUpdateTime(LocalDateTime.now());
        
        int i = sysDeptMapper.updateById(sysDeptRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DEPT_DELETE_FAIL);
        }
    }
    
    @Override
    public void modify(SysDeptModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 校验部门名称是否存在
        if (!this.checkUniqueName(param.getDeptName(), param.getDeptId(), param.getParentId())) {
            throw new BusinessException(ResultCode.Business.DEPT_NAME_IS_NOT_UNIQUE);
        }
        // 校验父级部门是否合法
        if (param.getParentId() != 0) {
            SysDept parentDeptInfo = sysDeptMapper.selectById(param.getParentId());
            if (parentDeptInfo == null || parentDeptInfo.getIsDeleted() || !parentDeptInfo.getStatus()
                    .equals(EnableStatusEnum.ENABLE.getCode())) {
                throw new BusinessException(ResultCode.Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED);
            }
            // 校验上级部门是否是自己
            if (param.getParentId().equals(param.getDeptId())) {
                throw new BusinessException(ResultCode.Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF);
            }
        }
        
        // 校验能否停用部门
        if (param.getStatus().equals(EnableStatusEnum.DISABLE.getCode())
                && sysDeptMapper.countNormalChildrenDeptById(param.getDeptId()) > 0) {
            throw new BusinessException(ResultCode.Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE);
        }
        // 停用部门,校验部门下是否存在用户
        if (param.getStatus().equals(EnableStatusEnum.DISABLE.getCode())
                && sysDeptMapper.countUserByDeptId(param.getDeptId()) > 0) {
            throw new BusinessException(ResultCode.Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_CHANGE);
        }
        
        SysDept sysDeptRecord = BeanUtil.copyProperties(param, SysDept.class);
        sysDeptRecord.setUpdateTime(LocalDateTime.now());
        int i = sysDeptMapper.updateById(sysDeptRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DEPT_MODIFY_FAIL);
        }
    }
    
    @Override
    public SysDeptVo get(Long deptId) {
        ValidatorUtil.validate(deptId);
        
        SysDept sysDeptInfo = sysDeptMapper.selectById(deptId);
        SysDeptVo vo = BeanUtil.copyProperties(sysDeptInfo, SysDeptVo.class);
        
        return vo;
    }
    
    @Override
    public List<SysDeptVo> list(SysDeptQuery param) {
        List<SysDeptVo> sysDeptList = sysDeptMapper.listAll(param);
        
        for (SysDeptVo sysDeptVO : sysDeptList) {
            int ancestorsLevel =
                    sysDeptVO.getAncestors().equals("0") ? 1 : sysDeptVO.getAncestors().split(",").length - 1;
            sysDeptVO.setAncestorsLevel(ancestorsLevel);
        }
        
        return sysDeptList;
    }
    
    @Override
    public Boolean checkUniqueName(String deptName, Long deptId, Long parentDeptId) {
        ValidatorUtil.validate(deptName, parentDeptId);
        
        deptId = deptId == null ? -1L : deptId;
        SysDeptVo sysDeptInfo = sysDeptMapper.getByDeptNameAndParentId(deptName, parentDeptId);
        if (sysDeptInfo != null && !deptId.equals(sysDeptInfo.getDeptId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @Override
    public List<Long> listDeptByRoleId(Long roleId) {
        SysRole sysRoleInfo = sysRoleMapper.selectById(roleId);
        return sysDeptMapper.listDeptByRoleId(roleId, sysRoleInfo.getDeptCheckStrictly());
    }
    
    @Override
    public List<TreeNode> listDeptTree(SysDeptQuery param) {
        List<SysDeptVo> deptList = this.list(param);
        List<TreeNode> nodes = deptList.stream().map(dept -> {
            TreeNode node = new TreeNode();
            node.setId(dept.getDeptId());
            node.setParentId(dept.getParentId());
            node.setLabel(dept.getDeptName());
            return node;
        }).collect(Collectors.toList());
        return this.buildTree(nodes, 0L);
    }
    
    
    /**
     * 构建部门树
     * @param nodeList 部门列表
     * @param rootId    根节点ID
     * @return 部门树
     */
    private List<TreeNode> buildTree(List<TreeNode> nodeList, Long rootId) {
        List<TreeNode> tree = new ArrayList<>();
        for (TreeNode node : nodeList) {
            if (rootId.equals(node.getParentId())) {
                node.setChildren(buildTree(nodeList, node.getId()));
                tree.add(node);
            }
        }
        return tree;
    }
}
