package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysNotice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "通知公告表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysNoticeVo extends SysNotice {

}
