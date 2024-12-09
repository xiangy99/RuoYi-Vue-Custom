package com.ruoyi.common.excel.annotation;


import com.ruoyi.common.excel.core.CellMergeStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 列单元格合并(合并列相同项)
 * <p>
 * 需搭配 {@link CellMergeStrategy} 策略使用
 *
 * @author xiangy
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellMerge {
    
    /**
     * col index
     */
    int index() default -1;
    
    /**
     * 合并需要依赖的其他字段名称
     */
    String[] mergeBy() default {};
    
}
