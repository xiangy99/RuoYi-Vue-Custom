package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysPost;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "岗位信息表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysPostVo extends SysPost {

}