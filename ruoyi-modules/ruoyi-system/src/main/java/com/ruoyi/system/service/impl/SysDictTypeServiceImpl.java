package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.enums.NormalDisableEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.common.redis.utils.CacheUtil;
import com.ruoyi.system.domain.bo.SysDictTypeModifyBo;
import com.ruoyi.system.domain.bo.SysDictTypeSaveBo;
import com.ruoyi.system.domain.pojo.SysDictType;
import com.ruoyi.system.domain.query.SysDictTypeQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;
import com.ruoyi.system.domain.vo.SysDictTypeVo;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * $SysDictTypeServiceImpl
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictTypeServiceImpl implements SysDictTypeService {
    
    private final SysDictTypeMapper sysDictTypeMapper;
    
    private final SysDictDataMapper sysDictDataMapper;
    
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#param.dictType")
    @Override
    public List<SysDictDataVo> save(SysDictTypeSaveBo param) {
        ValidatorUtil.validate(param);
        
        // 校验自定类型是否存在
        if (!this.checkUniqueType(param.getDictType(), param.getDictId())) {
            throw new BusinessException(ResultCode.Business.DICT_TYPE_IS_NOT_UNIQUE);
        }
        
        SysDictType sysDictTypeRecord = BeanUtil.copyProperties(param, SysDictType.class);
        sysDictTypeRecord.setDictId(IdUtil.getId());
        sysDictTypeRecord.setStatus(param.getStatus() != null ? param.getStatus() : NormalDisableEnum.ENABLE.getCode());
        sysDictTypeRecord.setCreateTime(LocalDateTime.now());
        sysDictTypeRecord.setUpdateTime(LocalDateTime.now());
        
        int i = sysDictTypeMapper.insert(sysDictTypeRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DICT_TYPE_SAVE_FAIL);
        }
        // 新增dictType没有dictData,所以无需查询dictData
        // 返回空集合是因为,会将返回结果设置到缓存,防止缓存穿透
        return Collections.emptyList();
    }
    
    @Override
    public void delete(Long[] dictTypeIds) {
        ValidatorUtil.validate(dictTypeIds);
        
        for (Long dictTypeId : dictTypeIds) {
            SysDictType sysDictTypeInfo = sysDictTypeMapper.selectById(dictTypeId);
            if (sysDictTypeInfo == null) {
                throw new BusinessException(ResultCode.Business.OBJECT_NOT_FOUND);
            }
            // 检查字典类型是否存在字典数据
            if (sysDictTypeMapper.countDictDataByDictType(sysDictTypeInfo.getDictType()) > 0) {
                throw new BusinessException(ResultCode.Business.DICT_TYPE_EXIST_DATA);
            }
            int i = sysDictTypeMapper.deleteById(dictTypeId);
            if (i != 1) {
                throw new BusinessException(ResultCode.Business.DICT_TYPE_DELETE_FAIL);
            }
            
            // 清空缓存
            CacheUtil.evict(CacheNames.SYS_DICT, sysDictTypeInfo.getDictType());
        }
    }
    
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#param.dictType")
    @Override
    public List<SysDictDataVo> modify(SysDictTypeModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 校验自定类型是否存在
        if (!this.checkUniqueType(param.getDictType(), param.getDictId())) {
            throw new BusinessException(ResultCode.Business.DICT_TYPE_IS_NOT_UNIQUE);
        }
        
        SysDictType sysDictTypeRecord = BeanUtil.copyProperties(param, SysDictType.class);
        sysDictTypeRecord.setUpdateTime(LocalDateTime.now());
        int i = sysDictTypeMapper.updateById(sysDictTypeRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DICT_TYPE_MODIFY_FAIL);
        }
        return sysDictDataMapper.listByDictType(param.getDictType());
    }
    
    @Override
    public SysDictTypeVo get(Long dictTypeId) {
        ValidatorUtil.validate(dictTypeId);
        
        SysDictType sysDictTypeInfo = sysDictTypeMapper.selectById(dictTypeId);
        SysDictTypeVo vo = BeanUtil.copyProperties(sysDictTypeInfo, SysDictTypeVo.class);
        
        return vo;
    }
    
    @Override
    public List<SysDictTypeVo> list(SysDictTypeQuery param) {
        List<SysDictTypeVo> sysDictTypeList = sysDictTypeMapper.listAll(param);
        
        return sysDictTypeList;
    }
    
    @Override
    public PageLight<SysDictTypeVo> page(SysDictTypeQuery param) {
        Page<SysDictTypeVo> sysDictTypeList = sysDictTypeMapper.page(param, PageUtil.getPage());
        return new PageLight<>(sysDictTypeList);
    }
    
    @Override
    public Boolean checkUniqueType(String dictType, Long dictId) {
        ValidatorUtil.validate(dictType);
        
        dictId = dictId == null ? -1 : dictId;
        SysDictTypeVo sysDictTypeInfo = sysDictTypeMapper.getByDictType(dictType);
        if (sysDictTypeInfo != null && !dictId.equals(sysDictTypeInfo.getDictId())) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    
    @Override
    public void resetDictCache() {
        CacheUtil.clear(CacheNames.SYS_DICT);
    }
}
