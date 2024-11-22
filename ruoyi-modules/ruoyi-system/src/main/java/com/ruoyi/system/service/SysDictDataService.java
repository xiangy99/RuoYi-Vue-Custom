package com.ruoyi.system.service;

import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysDictDataModifyBo;
import com.ruoyi.system.domain.bo.SysDictDataSaveBo;
import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;

import java.util.List;

/**
 * $SysDictDataService
 *
 * @author Link
 */
public interface SysDictDataService {
    
    List<SysDictDataVo> save(SysDictDataSaveBo param);
    
    void delete(Long[] dictDataCodes);
    
    List<SysDictDataVo> modify(SysDictDataModifyBo param);
    
    SysDictDataVo get(Long dictDataCode);
    
    List<SysDictDataVo> list(SysDictDataQuery param);
    
    PageLight<SysDictDataVo> page(SysDictDataQuery param);
    
    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataVo> listDictDateByDictType(String dictType);
    
    /**
     * 校验字典键值是否唯一
     *
     * @param dictCode  字典编码
     * @param dictValue 字典键值
     * @param dictType  字典类型
     * @return 结果
     */
    Boolean checkUniqueValue(Long dictCode, String dictValue, String dictType);
    
    
}
