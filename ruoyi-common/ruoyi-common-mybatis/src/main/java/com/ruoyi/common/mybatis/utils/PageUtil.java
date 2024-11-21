package com.ruoyi.common.mybatis.utils;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.mybatis.domain.BasePageQuery;

/**
 * @author coriander
 */
public class PageUtil {
    
    /**
     * 设置请求分页数据
     */
    public static <T> Page<T> getPage(BasePageQuery param) {
        Page<T> page = new Page<>(param.getPageNum(), param.getPageSize());
        if (CollUtil.isNotEmpty(param.getOrderList())) {
            page.setOrders(param.getOrderList());
        }
        return page;
    }
}

