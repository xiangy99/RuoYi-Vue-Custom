package com.ruoyi.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.demo.domain.pojo.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;

/**
 * @author Link
 * @date 2024-11-07
 */
public interface TestDemoService extends IService<TestDemo> {
    
    TestDemoVo getVo(Long id);
    
    PageLight<TestDemo> page();
}
