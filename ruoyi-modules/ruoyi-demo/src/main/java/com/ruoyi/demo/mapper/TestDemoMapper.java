package com.ruoyi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.demo.domain.pojo.TestDemo;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface TestDemoMapper extends BaseMapper<TestDemo> {
    
    Page<TestDemo> page(Page<TestDemo> param);
}