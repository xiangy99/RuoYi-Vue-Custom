package com.ruoyi.system.importlistener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.excel.core.ExcelListener;
import com.ruoyi.common.excel.core.ExcelResult;
import com.ruoyi.common.satoken.utils.SecurityUtils;
import com.ruoyi.system.domain.bo.SysUserModifyBo;
import com.ruoyi.system.domain.bo.SysUserSaveBo;
import com.ruoyi.system.domain.vo.SysUserVo;
import com.ruoyi.system.domain.vo.export.SysUserImportVo;
import com.ruoyi.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 系统用户自定义导入
 *
 */
@Slf4j
public class SysUserImportListener extends AnalysisEventListener<SysUserImportVo>
        implements ExcelListener<SysUserImportVo> {
    
    private final SysUserService sysUserService;
    
    // private final String password;
    
    private final Boolean isUpdateSupport;
    
    private final Long operateUserId;
    
    private int successNum = 0;
    
    private int failureNum = 0;
    
    private final StringBuilder successMsg = new StringBuilder();
    
    private final StringBuilder failureMsg = new StringBuilder();
    
    public SysUserImportListener(Boolean isUpdateSupport) {
        // String initPassword = SpringUtils.getBean(SysDictTypeService.class).selectConfigByKey("sys.user.initPassword");
        this.sysUserService = SpringUtil.getBean(SysUserService.class);
        // this.password = BCrypt.hashpw(initPassword);
        this.isUpdateSupport = isUpdateSupport;
        this.operateUserId = SecurityUtils.getUserId();
    }
    
    @Override
    public void invoke(SysUserImportVo userVo, AnalysisContext context) {
        SysUserVo sysUser = sysUserService.getByUserName(userVo.getUserName());
        try {
            // 验证是否存在这个用户
            if (ObjectUtil.isNull(sysUser)) {
                SysUserSaveBo user = BeanUtil.toBean(userVo, SysUserSaveBo.class);
                ValidatorUtil.validate(user);
                // user.setPassword(password);
                user.setCreateBy(operateUserId.toString());
                sysUserService.save(user);
                successNum++;
                successMsg.append("<br/>").append(successNum).append("、账号 ").append(user.getUserName())
                        .append(" 导入成功");
            } else if (isUpdateSupport) {
                Long userId = sysUser.getUserId();
                SysUserModifyBo user = BeanUtil.toBean(userVo, SysUserModifyBo.class);
                user.setUserId(userId);
                ValidatorUtil.validate(user);
                // sysUserService.checkUserAllowed(user.getUserId());
                // sysUserService.checkUserDataScope(user.getUserId());
                // user.setUpdateBy(operateUserId);
                sysUserService.modify(user);
                successNum++;
                successMsg.append("<br/>").append(successNum).append("、账号 ").append(user.getUserName())
                        .append(" 更新成功");
            } else {
                failureNum++;
                failureMsg.append("<br/>").append(failureNum).append("、账号 ").append(sysUser.getUserName())
                        .append(" 已存在");
            }
        } catch (Exception e) {
            failureNum++;
            String msg = "<br/>" + failureNum + "、账号 " + userVo.getUserName() + " 导入失败：";
            failureMsg.append(msg).append(e.getMessage());
            log.error(msg, e);
        }
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    
    }
    
    @Override
    public ExcelResult<SysUserImportVo> getExcelResult() {
        return new ExcelResult<>() {
            
            @Override
            public String getAnalysis() {
                if (failureNum > 0) {
                    failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
                    throw new BusinessException(failureMsg.toString());
                } else {
                    successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
                }
                return successMsg.toString();
            }
            
            @Override
            public List<SysUserImportVo> getList() {
                return null;
            }
            
            @Override
            public List<String> getErrorList() {
                return null;
            }
        };
    }
}
