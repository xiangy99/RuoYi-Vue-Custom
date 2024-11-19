package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysDictType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "字典类型表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysDictTypeVo extends SysDictType {

}
