package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysUserPost;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@Schema(description = "用户与岗位关联表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysUserPostVo extends SysUserPost {

}
