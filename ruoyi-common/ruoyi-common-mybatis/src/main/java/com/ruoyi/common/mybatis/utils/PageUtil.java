package com.ruoyi.common.mybatis.utils;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.mybatis.domain.PageDomain;
import com.ruoyi.common.mybatis.domain.PageSupport;

import java.util.List;

/**
 * @author coriander
 */
public class PageUtil {
    
    /**
     * 设置请求分页数据
     */
    public static <T> Page<T> getPage() {
        PageDomain pageDomain = PageSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        List<OrderItem> orderList = pageDomain.getOrderList();
        Page<T> page = new Page<>(pageNum, pageSize);
        if (CollUtil.isNotEmpty(orderList)) {
            page.setOrders(orderList);
        }
        return page;
    }
}

