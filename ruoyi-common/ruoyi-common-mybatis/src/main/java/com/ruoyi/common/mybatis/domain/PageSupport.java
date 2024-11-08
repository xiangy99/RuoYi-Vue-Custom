package com.ruoyi.common.mybatis.domain;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.utils.ServletUtil;

import java.util.List;

/**
 * @author coriander
 */
public class PageSupport {
    
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";
    
    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    
    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN_LIST = "orderListStr";
    
    
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Convert.toInt(ServletUtil.getParameter(PAGE_NUM), 1));
        pageDomain.setPageSize(Convert.toInt(ServletUtil.getParameter(PAGE_SIZE), 10));
        String orderListStr = ServletUtil.getParameter(ORDER_BY_COLUMN_LIST);
        List<OrderItem> orderItemList = null;
        try {
            orderItemList = JSONObject.parseArray(orderListStr, OrderItem.class);
        } catch (Exception e) {
            throw new BusinessException("排序参数格式错误");
        }
        pageDomain.setOrderList(orderItemList);
        return pageDomain;
    }
    
    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
    
}
