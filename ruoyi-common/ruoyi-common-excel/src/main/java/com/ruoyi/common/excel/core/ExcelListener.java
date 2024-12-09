package com.ruoyi.common.excel.core;

import com.alibaba.excel.read.listener.ReadListener;

/**
 * Excel 导入监听
 *
 * @author xiangy
 */
public interface ExcelListener<T> extends ReadListener<T> {
    
    ExcelResult<T> getExcelResult();
    
}
