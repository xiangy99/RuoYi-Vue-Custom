package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.pojo.SysUserRole;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Link
 * @date 2024-11-08
 */
@AutoMapper(target = SysUserRole.class)
@Schema(description = "用户和角色关联表 Vo")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class SysUserRoleVo extends SysUserRole {

}
