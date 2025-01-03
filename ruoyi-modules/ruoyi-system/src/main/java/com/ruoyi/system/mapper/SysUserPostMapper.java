package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.pojo.SysUserPost;
import org.apache.ibatis.annotations.Param;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {
    
    int deleteByUserId(@Param("userId") Long userId);
}