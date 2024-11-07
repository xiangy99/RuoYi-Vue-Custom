package com.ruoyi.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.demo.domain.pojo.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.mapper.TestDemoMapper;
import com.ruoyi.demo.service.TestDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Link
 * @date 2024-11-07
 */
@Service
@RequiredArgsConstructor
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements TestDemoService {
    
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final TestDemoMapper testDemoMapper;
    
    @Override
    public TestDemoVo getVo(Long id) {
        return testDemoMapper.getVo(id);
    }
    
    @Override
    public PageLight<TestDemo> page() {
        Page<TestDemo> page = new Page<>(1, 10);
        
        Page<TestDemo> page1 = testDemoMapper.page(page);
        return new PageLight<>(page1);
    }
}
