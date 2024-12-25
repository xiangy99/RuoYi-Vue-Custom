package com.ruoyi.system.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */

/**
 * 通知公告表
 */
@Schema(description = "通知公告表")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysNotice extends BaseEntity implements Serializable {
    
    /**
     * 公告ID
     */
    @TableId
    @Schema(description = "公告ID")
    @NotNull(message = "公告ID不能为null")
    private Integer noticeId;
    
    /**
     * 公告标题
     */
    @Schema(description = "公告标题")
    @Size(max = 50, message = "公告标题最大长度要小于 50")
    @NotBlank(message = "公告标题不能为空")
    private String noticeTitle;
    
    /**
     * 公告类型（1通知 2公告）
     */
    @Schema(description = "公告类型（1通知 2公告）")
    @Size(max = 1, message = "公告类型（1通知 2公告）最大长度要小于 1")
    @NotBlank(message = "公告类型（1通知 2公告）不能为空")
    private String noticeType;
    
    /**
     * 公告内容
     */
    @Schema(description = "公告内容")
    private byte[] noticeContent;
    
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    
    /**
     * 公告状态（0正常 1关闭）
     */
    @Schema(description = "公告状态（0正常 1关闭）")
    @Size(max = 1, message = "公告状态（0正常 1关闭）最大长度要小于 1")
    private String status;
    
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注最大长度要小于 500")
    private String remark;
    
    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    private Boolean isDeleted;
}