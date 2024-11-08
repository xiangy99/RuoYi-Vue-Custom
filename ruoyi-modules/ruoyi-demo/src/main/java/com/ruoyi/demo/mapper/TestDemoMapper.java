package com.ruoyi.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.mybatis.mapper.BaseMapperPlus;
import com.ruoyi.demo.domain.pojo.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface TestDemoMapper extends BaseMapperPlus<TestDemo, TestDemoVo> {
    
    Page<TestDemo> page(Page<TestDemo> param);
}