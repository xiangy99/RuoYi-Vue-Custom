package com.ruoyi.demo.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.annotation.ExcelEnumFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import com.ruoyi.common.excel.convert.ExcelEnumConvert;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 带有下拉选的Excel导出
 *
 * @author xiangy
 */
@Data
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
public class ExportDemoVo {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户昵称
     */
    @ExcelProperty(value = "用户名", index = 0)
    @NotEmpty(message = "用户名不能为空")
    private String nickName;
    
    /**
     * 用户类型
     * </p>
     * 使用ExcelEnumFormat注解需要进行下拉选的部分
     */
    @ExcelProperty(value = "用户类型", index = 1, converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = EnableStatusEnum.class, textField = "value")
    @NotEmpty(message = "用户类型不能为空")
    private String userStatus;
    
    /**
     * 性别
     * <p>
     * 使用ExcelDictFormat注解需要进行下拉选的部分
     */
    @ExcelProperty(value = "性别", index = 2, converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    @NotEmpty(message = "性别不能为空")
    private String gender;
    
    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号", index = 3)
    @NotEmpty(message = "手机号不能为空")
    private String phoneNumber;
    
    /**
     * Email
     */
    @ExcelProperty(value = "Email", index = 4)
    @NotEmpty(message = "Email不能为空")
    private String email;
    
    /**
     * 省
     * <p>
     * 级联下拉，仅判断是否选了
     */
    @ExcelProperty(value = "省", index = 5)
    @NotNull(message = "省不能为空")
    private String province;
    
    /**
     * 数据库中的省ID
     * </p>
     * 处理完毕后再判断是否市正确的值
     */
    private Integer provinceId;
    
    /**
     * 市
     * <p>
     * 级联下拉
     */
    @ExcelProperty(value = "市", index = 6)
    @NotNull(message = "市不能为空")
    private String city;
    
    /**
     * 数据库中的市ID
     */
    private Integer cityId;
    
    /**
     * 县
     * <p>
     * 级联下拉
     */
    @ExcelProperty(value = "县", index = 7)
    @NotNull(message = "县不能为空")
    private String area;
    
    /**
     * 数据库中的县ID
     */
    private Integer areaId;
}
