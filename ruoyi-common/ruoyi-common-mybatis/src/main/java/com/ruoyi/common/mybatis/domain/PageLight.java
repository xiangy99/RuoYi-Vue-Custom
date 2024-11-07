package com.ruoyi.common.mybatis.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author coriander
 */
@Getter
@Setter
public class PageLight<T> implements Serializable {
    
    private static final long serialVersionUID = 5865250330571801592L;
    
    /**
     * 总记录数
     */
    private Integer total;
    
    /**
     * 列表数据
     */
    private List<T> rows;
    
    public PageLight() {
    }
    
    
    public PageLight(IPage<T> page) {
        this.total = Integer.parseInt(String.valueOf(page.getTotal()));
        this.rows = page.getRecords();
    }
    
}
