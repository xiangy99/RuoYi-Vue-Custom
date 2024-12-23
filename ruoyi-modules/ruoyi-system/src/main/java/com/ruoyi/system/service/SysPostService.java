package com.ruoyi.system.service;

import com.ruoyi.common.mybatis.domain.PageLight;
import com.ruoyi.system.domain.bo.SysPostModifyBo;
import com.ruoyi.system.domain.bo.SysPostSaveBo;
import com.ruoyi.system.domain.query.SysPostQuery;
import com.ruoyi.system.domain.vo.SysPostVo;

import java.util.List;

/**
 * $SysPostService
 *
 * @author Link
 */
public interface SysPostService {
    
    void save(SysPostSaveBo param);
    
    void delete(Long[] postIds);
    
    void modify(SysPostModifyBo param);
    
    SysPostVo get(Long postId);
    
    List<SysPostVo> list(SysPostQuery param);
    
    PageLight<SysPostVo> page(SysPostQuery param);
    
    /**
     * 校验岗位名称是否唯一
     *
     * @param postName 岗位名称
     * @param postId   岗位ID
     * @return 结果
     */
    Boolean checkUniqueName(String postName, Long postId);
    
    /**
     * 校验岗位编码是否唯一
     *
     * @param postCode 岗位编码
     * @param postId 岗位ID
     * @return 结果
     */
    Boolean checkUniqueCode(String postCode, Long postId);
    
}
