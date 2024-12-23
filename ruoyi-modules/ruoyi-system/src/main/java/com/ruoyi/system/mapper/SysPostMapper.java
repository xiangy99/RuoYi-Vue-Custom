package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.pojo.SysPost;
import com.ruoyi.system.domain.query.SysPostQuery;
import com.ruoyi.system.domain.vo.SysPostVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Link
 * @date 2024-11-08
 */
public interface SysPostMapper extends BaseMapper<SysPost> {
    
    SysPostVo getByPostName(@Param("postName") String postName);
    
    SysPostVo getByPostCode(@Param("postCode") String postCode);
    
    /**
     * 根据岗位ID查询用户数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    int countUserByPostId(@Param("postId") Long postId);
    
    List<SysPostVo> listAll(SysPostQuery param);
    
    Page<SysPostVo> page(@Param("param") SysPostQuery param, Page<SysPostQuery> page);
    
}