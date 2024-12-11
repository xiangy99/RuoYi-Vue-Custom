package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysDictType;
import com.ruoyi.system.domain.query.SysDictTypeQuery;
import com.ruoyi.system.domain.vo.SysDictTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    
    SysDictTypeVo getByDictType(@Param("dictType") String dictType);
    
    /**
     * 查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据
     */
    int countDictDataByDictType(@Param("dictType") String dictType);
    
    List<SysDictTypeVo> listAll(SysDictTypeQuery param);
    
    Page<SysDictTypeVo> page(@Param("param") SysDictTypeQuery param, Page<SysDictTypeQuery> page);
}