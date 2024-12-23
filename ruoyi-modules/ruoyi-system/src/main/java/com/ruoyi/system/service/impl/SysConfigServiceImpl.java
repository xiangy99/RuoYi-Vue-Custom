package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.common.redis.utils.CacheUtil;
import com.ruoyi.system.domain.bo.SysConfigModifyBo;
import com.ruoyi.system.domain.bo.SysConfigSaveBo;
import com.ruoyi.system.domain.pojo.SysConfig;
import com.ruoyi.system.domain.query.SysConfigQuery;
import com.ruoyi.system.domain.vo.SysConfigVo;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * $SysConfigServiceImpl
 *
 * @author Link
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysConfigServiceImpl implements SysConfigService {
    
    private final SysConfigMapper sysConfigMapper;
    
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#param.configKey")
    @Override
    public String save(SysConfigSaveBo param) {
        ValidatorUtil.validate(param);
        
        // 检查参数键值是否存在
        if (!this.checkUniqueKey(param.getConfigId(), param.getConfigKey())) {
            throw new BusinessException(ResultCode.Business.CONFIG_KEY_IS_NOT_UNIQUE);
        }
        
        SysConfig sysConfigRecord = BeanUtil.copyProperties(param, SysConfig.class);
        sysConfigRecord.setConfigId(IdUtil.getId());
        sysConfigRecord.setCreateTime(LocalDateTime.now());
        sysConfigRecord.setUpdateTime(LocalDateTime.now());
        
        int i = sysConfigMapper.insert(sysConfigRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.CONFIG_SAVE_FAIL);
        }
        return sysConfigRecord.getConfigValue();
    }
    
    @Override
    public void delete(Long[] configIds) {
        ValidatorUtil.validate(configIds);
        
        for (Long configId : configIds) {
            SysConfig sysConfigInfo = sysConfigMapper.selectById(configId);
            if (sysConfigInfo == null) {
                throw new BusinessException(ResultCode.Business.OBJECT_NOT_FOUND);
            }
            if (sysConfigInfo.getIsDefault() != null && sysConfigInfo.getIsDefault()) {
                throw new BusinessException(ResultCode.Business.CONFIG_CAN_NOT_DELETE_DEFAULT);
            }
            sysConfigMapper.deleteById(configId);
            CacheUtil.evict(CacheNames.SYS_CONFIG, sysConfigInfo.getConfigKey());
        }
    }
    
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#param.configKey")
    @Override
    public String modify(SysConfigModifyBo param) {
        ValidatorUtil.validate(param);
        
        // 检查参数键值是否存在
        if (!this.checkUniqueKey(param.getConfigId(), param.getConfigKey())) {
            throw new BusinessException(ResultCode.Business.CONFIG_KEY_IS_NOT_UNIQUE);
        }
        
        SysConfig sysConfigRecord = BeanUtil.copyProperties(param, SysConfig.class);
        sysConfigRecord.setUpdateTime(LocalDateTime.now());
        int i = sysConfigMapper.updateById(sysConfigRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.CONFIG_MODIFY_FAIL);
        }
        return sysConfigRecord.getConfigValue();
    }
    
    @Override
    public SysConfigVo get(Long configId) {
        ValidatorUtil.validate(configId);
        
        SysConfig sysConfigInfo = sysConfigMapper.selectById(configId);
        SysConfigVo vo = BeanUtil.copyProperties(sysConfigInfo, SysConfigVo.class);
        
        return vo;
    }
    
    @Override
    public List<SysConfigVo> list(SysConfigQuery param) {
        List<SysConfigVo> list = sysConfigMapper.listAll(param).stream()
                .map(v -> BeanUtil.copyProperties(v, SysConfigVo.class)).collect(Collectors.toList());
        return list;
    }
    
    @Override
    public PageLight<SysConfigVo> page(SysConfigQuery param) {
        Page<SysConfigVo> list = sysConfigMapper.page(param, PageUtil.getPage(param));
        return new PageLight<>(list);
    }
    
    @Override
    public Boolean checkUniqueKey(Long configId, String configKey) {
        ValidatorUtil.validate(configKey);
        
        configId = configId == null ? -1 : configId;
        SysConfig sysConfigInfo = sysConfigMapper.getByConfigKey(configKey);
        if (sysConfigInfo != null && !configId.equals(sysConfigInfo.getConfigId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @Override
    public void resetConfigCache() {
        CacheUtil.clear(CacheNames.SYS_CONFIG);
    }
}
