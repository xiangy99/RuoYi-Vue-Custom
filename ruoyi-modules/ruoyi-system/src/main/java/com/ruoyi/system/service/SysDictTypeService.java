package com.ruoyi.system.service;


import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysDictTypeModifyBo;
import com.ruoyi.system.domain.bo.SysDictTypeSaveBo;
import com.ruoyi.system.domain.query.SysDictTypeQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;
import com.ruoyi.system.domain.vo.SysDictTypeVo;

import java.util.List;

/**
 * $SysDictTypeService
 *
 * @author Link
 */
public interface SysDictTypeService {
    
    List<SysDictDataVo> save(SysDictTypeSaveBo param);
    
    void delete(Long[] dictTypeIds);
    
    List<SysDictDataVo> modify(SysDictTypeModifyBo param);
    
    SysDictTypeVo get(Long dictTypeId);
    
    List<SysDictTypeVo> list(SysDictTypeQuery param);
    
    PageLight<SysDictTypeVo> page(SysDictTypeQuery param);
    
    Boolean checkUniqueType(String dictType, Long dictId);
    
    /**
     * 重置字典缓存数据
     */
    void resetDictCache();
}
