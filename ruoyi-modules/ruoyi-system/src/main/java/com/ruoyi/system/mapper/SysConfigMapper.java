package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysConfig;
import com.ruoyi.system.domain.query.SysConfigQuery;
import com.ruoyi.system.domain.vo.SysConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    
    List<SysConfig> listAll(SysConfigQuery param);
    
    Page<SysConfigVo> page(@Param("param") SysConfigQuery param, Page<SysConfigQuery> query);
    
    SysConfig getByConfigKey(@Param("configKey") String configKey);
    
}