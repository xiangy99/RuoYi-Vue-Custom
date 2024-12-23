package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.pojo.SysDept;
import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.domain.vo.SysDeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {
    
    /**
     * 查询部门列表
     *
     * @param param 部门信息
     * @return 部门信息集合
     */
    List<SysDeptVo> listAll(@Param("param") SysDeptQuery param);
    
    SysDeptVo getByDeptNameAndParentId(@Param("deptName") String deptName, @Param("parentId") Long parentId);
    
    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    int countNormalChildrenDeptById(@Param("deptId") Long deptId);
    
    /**
     * 是否存在正在使用的子部门
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int countChildByDeptId(@Param("deptId") Long deptId);
    
    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int countUserByDeptId(@Param("deptId") Long deptId);
    
    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    List<Long> listDeptByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);
}