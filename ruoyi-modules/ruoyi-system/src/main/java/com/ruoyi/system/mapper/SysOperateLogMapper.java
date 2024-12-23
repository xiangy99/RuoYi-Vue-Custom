package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysOperateLog;
import com.ruoyi.system.domain.query.SysOperateLogQuery;
import com.ruoyi.system.domain.vo.SysOperateLogVo;
import org.apache.ibatis.annotations.Param;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysOperateLogMapper extends BaseMapper<SysOperateLog> {
    
    Page<SysOperateLogVo> page(@Param("param") SysOperateLogQuery param, Page<SysOperateLogQuery> page);
}