package com.ruoyi.common.mybatis.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author coriander
 */
@Schema(description = "分页结果")
@Getter
@Setter
@NoArgsConstructor
public class PageLight<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 5865250330571801592L;
    
    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private Integer total;
    
    /**
     * 列表数据
     */
    @Schema(description = "列表数据")
    private List<T> rows;
    
    
    public PageLight(IPage<T> page) {
        this.total = Integer.parseInt(String.valueOf(page.getTotal()));
        this.rows = page.getRecords();
    }
    
}
