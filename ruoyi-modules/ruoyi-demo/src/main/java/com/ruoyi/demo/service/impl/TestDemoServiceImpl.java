package com.ruoyi.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.mybatis.domain.BasePageQuery;
import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.common.mybatis.utils.PageUtil;
import com.ruoyi.demo.domain.pojo.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.mapper.TestDemoMapper;
import com.ruoyi.demo.service.TestDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Link
 */
@Service
@RequiredArgsConstructor
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements TestDemoService {
    
    private final TestDemoMapper testDemoMapper;
    
    @Override
    public TestDemoVo getVo(Long id) {
        TestDemo testDemo = testDemoMapper.selectById(id);
        return BeanUtil.copyProperties(testDemo, TestDemoVo.class);
    }
    
    @Override
    public PageLight<TestDemo> page() {
        Page<TestDemo> page1 = testDemoMapper.page(PageUtil.getPage(new BasePageQuery()));
        return new PageLight<>(page1);
    }
}
