package com.ruoyi.system.service.impl;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.service.DictService;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.StreamUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.common.redis.utils.CacheUtil;
import com.ruoyi.system.domain.bo.SysDictDataModifyBo;
import com.ruoyi.system.domain.bo.SysDictDataSaveBo;
import com.ruoyi.system.domain.pojo.SysDictData;
import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;
import com.ruoyi.system.domain.vo.SysDictTypeVo;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.service.SysDictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * $SysDictDataServiceImPL
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictDataServiceImpl implements SysDictDataService, DictService {
    
    private final SysDictDataMapper sysDictDataMapper;
    
    private final SysDictTypeMapper sysDictTypeMapper;
    
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#param.dictType")
    @Override
    public List<SysDictDataVo> save(SysDictDataSaveBo param) {
        ValidatorUtil.validate(param);
        
        // 校验字典键值是否存在
        if (!this.checkUniqueValue(param.getDictCode(), param.getDictValue(), param.getDictType())) {
            throw new BusinessException(ResultCode.Business.DICT_DATA_LABEL_IS_NOT_UNIQUE);
        }
        
        SysDictData sysDictDataRecord = BeanUtil.copyProperties(param, SysDictData.class);
        sysDictDataRecord.setDictCode(IdUtil.getId());
        sysDictDataRecord.setStatus(param.getStatus() != null ? param.getStatus() : EnableStatusEnum.ENABLE.getCode());
        sysDictDataRecord.setCreateTime(LocalDateTime.now());
        sysDictDataRecord.setUpdateTime(LocalDateTime.now());
        
        int i = sysDictDataMapper.insert(sysDictDataRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DICT_DATA_SAVE_FAIL);
        }
        return sysDictDataMapper.listByDictType(param.getDictType());
    }
    
    @Override
    public void delete(Long[] dictDataCodes) {
        ValidatorUtil.validate(dictDataCodes);
        
        for (Long dictDataCode : dictDataCodes) {
            SysDictData sysDictData = sysDictDataMapper.selectById(dictDataCode);
            if (sysDictData == null) {
                throw new BusinessException(ResultCode.Business.OBJECT_NOT_FOUND);
            }
            sysDictDataMapper.deleteById(dictDataCode);
            CacheUtil.evict(CacheNames.SYS_DICT, sysDictData.getDictType());
        }
    }
    
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#param.dictType")
    @Override
    public List<SysDictDataVo> modify(SysDictDataModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 校验字典键值是否存在
        if (!this.checkUniqueValue(param.getDictCode(), param.getDictValue(), param.getDictType())) {
            throw new BusinessException(ResultCode.Business.DICT_DATA_LABEL_IS_NOT_UNIQUE);
        }
        
        SysDictData sysDictDataRecord = BeanUtil.copyProperties(param, SysDictData.class);
        sysDictDataRecord.setUpdateTime(LocalDateTime.now());
        int i = sysDictDataMapper.updateById(sysDictDataRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.DICT_DATA_MODIFY_FAIL);
        }
        return sysDictDataMapper.listByDictType(param.getDictType());
    }
    
    @Override
    public SysDictDataVo get(Long dictDataCode) {
        ValidatorUtil.validate(dictDataCode);
        
        SysDictData sysDictDataInfo = sysDictDataMapper.selectById(dictDataCode);
        SysDictDataVo vo = BeanUtil.copyProperties(sysDictDataInfo, SysDictDataVo.class);
        
        return vo;
    }
    
    @Override
    public List<SysDictDataVo> list(SysDictDataQuery param) {
        List<SysDictDataVo> list = sysDictDataMapper.listAll(param);
        
        return list;
    }
    
    @Override
    public PageLight<SysDictDataVo> page(SysDictDataQuery param) {
        Page<SysDictDataVo> list = sysDictDataMapper.page(param, PageUtil.getPage(param));
        
        return new PageLight<>(list);
    }
    
    @Cacheable(cacheNames = CacheNames.SYS_DICT, key = "#dictType")
    @Override
    public List<SysDictDataVo> listDictDateByDictType(String dictType) {
        ValidatorUtil.validate(dictType);
        // 查询类型是否停用
        SysDictTypeVo dictTypeResult = sysDictTypeMapper.getByDictType(dictType);
        if (dictTypeResult == null || EnableStatusEnum.DISABLE.getCode().equals(dictTypeResult.getStatus())) {
            return Collections.emptyList();
        }
        
        List<SysDictDataVo> dicDataList = sysDictDataMapper.listByDictType(dictType);
        if (CollUtil.isNotEmpty(dicDataList)) {
            return dicDataList;
        }
        return Collections.emptyList();
    }
    
    @Override
    public Boolean checkUniqueValue(Long dictCode, String dictValue, String dictType) {
        ValidatorUtil.validate(dictValue, dictType);
        
        dictCode = dictCode == null ? -1 : dictCode;
        SysDictDataVo sysDictDataInfo = sysDictDataMapper.getByDictTypeAndDictValue(dictType, dictValue);
        if (sysDictDataInfo != null && !dictCode.equals(sysDictDataInfo.getDictCode())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    
    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictType  字典类型
     * @param dictValue 字典值
     * @param separator 分隔符
     * @return 字典标签
     */
    @Override
    public String getDictLabel(String dictType, String dictValue, String separator) {
        List<SysDictDataVo> list = ProxyUtil.proxy(this, TimeIntervalAspect.class).listDictDateByDictType(dictType);
        
        Map<String, String> map = StreamUtil.toMap(list, SysDictDataVo::getDictValue, SysDictDataVo::getDictLabel);
        if (StringUtils.containsAny(dictValue, separator)) {
            return Arrays.stream(dictValue.split(separator)).map(v -> map.getOrDefault(v, StringUtils.EMPTY))
                    .collect(Collectors.joining(separator));
        } else {
            return map.getOrDefault(dictValue, StringUtils.EMPTY);
        }
    }
    
    /**
     * 根据字典类型和字典标签获取字典值
     *
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @param separator 分隔符
     * @return 字典值
     */
    @Override
    public String getDictValue(String dictType, String dictLabel, String separator) {
        List<SysDictDataVo> list = ProxyUtil.proxy(this, TimeIntervalAspect.class).listDictDateByDictType(dictType);
        Map<String, String> map = StreamUtil.toMap(list, SysDictDataVo::getDictLabel, SysDictDataVo::getDictValue);
        if (StringUtils.containsAny(dictLabel, separator)) {
            return Arrays.stream(dictLabel.split(separator)).map(l -> map.getOrDefault(l, StringUtils.EMPTY))
                    .collect(Collectors.joining(separator));
        } else {
            return map.getOrDefault(dictLabel, StringUtils.EMPTY);
        }
    }
    
    @Override
    public Map<String, String> getAllDictByDictType(String dictType) {
        List<SysDictDataVo> list = ProxyUtil.proxy(this, TimeIntervalAspect.class).listDictDateByDictType(dictType);
        return StreamUtil.toMap(list, SysDictDataVo::getDictValue, SysDictDataVo::getDictLabel);
    }
    
}
