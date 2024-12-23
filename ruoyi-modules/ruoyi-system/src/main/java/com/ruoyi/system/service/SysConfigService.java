package com.ruoyi.system.service;

import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysConfigModifyBo;
import com.ruoyi.system.domain.bo.SysConfigSaveBo;
import com.ruoyi.system.domain.query.SysConfigQuery;
import com.ruoyi.system.domain.vo.SysConfigVo;

import java.util.List;

/**
 * $SysConfigService
 *
 * @author Link
 */
public interface SysConfigService {
    
    String save(SysConfigSaveBo param);
    
    void delete(Long[] configIds);
    
    String modify(SysConfigModifyBo param);
    
    SysConfigVo get(Long configId);
    
    List<SysConfigVo> list(SysConfigQuery param);
    
    PageLight<SysConfigVo> page(SysConfigQuery param);
    
    /**
     * 校验参数键名是否唯一
     *
     * @param configId 参数主键
     * @param configKey 参数键值
     * @return 结果
     */
    Boolean checkUniqueKey(Long configId, String configKey);
    
    /**
     * 重置参数缓存数据
     */
    void resetConfigCache();
    
    
}
