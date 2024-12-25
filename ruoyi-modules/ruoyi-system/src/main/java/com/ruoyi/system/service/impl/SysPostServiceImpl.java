package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.core.enums.YesOrNoEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.system.domain.bo.SysPostModifyBo;
import com.ruoyi.system.domain.bo.SysPostSaveBo;
import com.ruoyi.system.domain.pojo.SysPost;
import com.ruoyi.system.domain.query.SysPostQuery;
import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.service.SysPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * $SysPostServiceImpl
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysPostServiceImpl implements SysPostService {
    
    private final SysPostMapper sysPostMapper;
    
    @Override
    public void save(SysPostSaveBo param) {
        ValidatorUtil.validate(param);
        
        // 校验岗位名称是否存在
        if (!this.checkUniqueName(param.getPostName(), param.getPostId())) {
            throw new BusinessException(ResultCode.Business.POST_NAME_IS_NOT_UNIQUE);
        }
        
        // 校验岗位编码是否存在
        if (!this.checkUniqueCode(param.getPostCode(), param.getPostId())) {
            throw new BusinessException(ResultCode.Business.POST_CODE_IS_NOT_UNIQUE);
        }
        
        SysPost sysPostRecord = BeanUtil.copyProperties(param, SysPost.class);
        sysPostRecord.setPostId(IdUtil.getId());
        sysPostRecord.setStatus(param.getStatus() == null ? EnableStatusEnum.ENABLE.getCode() : param.getStatus());
        sysPostRecord.setIsDeleted(YesOrNoEnum.NO.getCode());
        sysPostRecord.setCreateTime(LocalDateTime.now());
        sysPostRecord.setUpdateTime(LocalDateTime.now());
        int i = sysPostMapper.insert(sysPostRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.POST_SAVE_FAIL);
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long[] postIds) {
        ValidatorUtil.validate(postIds);
        
        for (Long postId : postIds) {
            // 检查角色是否被用户使用
            if (sysPostMapper.countUserByPostId(postId) > 0) {
                throw new BusinessException(ResultCode.Business.POST_ALREADY_ASSIGN_TO_USER);
            }
            
            SysPost sysPostRecord = new SysPost();
            sysPostRecord.setPostId(postId);
            sysPostRecord.setIsDeleted(YesOrNoEnum.YES.getCode());
            sysPostRecord.setUpdateTime(LocalDateTime.now());
            
            int i = sysPostMapper.updateById(sysPostRecord);
            if (i != 1) {
                throw new BusinessException(ResultCode.Business.POST_DELETE_FAIL);
            }
            
        }
    }
    
    @Override
    public void modify(SysPostModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 校验岗位名称是否存在
        if (!this.checkUniqueName(param.getPostName(), param.getPostId())) {
            throw new BusinessException(ResultCode.Business.POST_NAME_IS_NOT_UNIQUE);
        }
        
        // 校验岗位编码是否存在
        if (!this.checkUniqueCode(param.getPostCode(), param.getPostId())) {
            throw new BusinessException(ResultCode.Business.POST_CODE_IS_NOT_UNIQUE);
        }
        
        SysPost sysPostRecord = BeanUtil.copyProperties(param, SysPost.class);
        sysPostRecord.setUpdateTime(LocalDateTime.now());
        int i = sysPostMapper.updateById(sysPostRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.POST_MODIFY_FAIL);
        }
    }
    
    @Override
    public SysPostVo get(Long postId) {
        ValidatorUtil.validate(postId);
        
        SysPost sysPostInfo = sysPostMapper.selectById(postId);
        SysPostVo vo = BeanUtil.copyProperties(sysPostInfo, SysPostVo.class);
        
        return vo;
    }
    
    @Override
    public List<SysPostVo> list(SysPostQuery param) {
        List<SysPostVo> sysPostList = sysPostMapper.listAll(param);
        
        return sysPostList;
    }
    
    @Override
    public PageLight<SysPostVo> page(SysPostQuery param) {
        Page<SysPostVo> sysPostVoList = sysPostMapper.page(param, PageUtil.getPage(param));
        return new PageLight<>(sysPostVoList);
    }
    
    @Override
    public Boolean checkUniqueName(String postName, Long postId) {
        ValidatorUtil.validate(postName);
        
        postId = postId == null ? -1L : postId;
        SysPostVo sysPostInfo = sysPostMapper.getByPostName(postName);
        if (sysPostInfo != null && !postId.equals(sysPostInfo.getPostId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @Override
    public Boolean checkUniqueCode(String postCode, Long postId) {
        ValidatorUtil.validate(postCode);
        
        postId = postId == null ? -1L : postId;
        SysPostVo sysPostInfo = sysPostMapper.getByPostCode(postCode);
        if (sysPostInfo != null && !postId.equals(sysPostInfo.getPostId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
