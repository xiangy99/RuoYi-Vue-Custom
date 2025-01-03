package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysDictData;
import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.domain.vo.SysDictDataVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    
    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataVo> listByDictType(@Param("dictType") String dictType);
    
    List<SysDictDataVo> listAll(SysDictDataQuery param);
    
    Page<SysDictDataVo> page(@Param("param") SysDictDataQuery param, Page<SysDictDataQuery> page);
    
    SysDictDataVo getByDictTypeAndDictValue(@Param("dictType") String dictType, @Param("dictValue") String dictValue);
}