package com.ruoyi.common.core.result;

import cn.hutool.json.JSONUtil;

/**
 * 系统内的模块
 *
 * @author coriander
 */
public enum Module {
    
    /**
     * 通用
     */
    COMMON(0),
    
    /**
     * 用户
     */
    USER(1),
    
    /**
     * 部门
     */
    DEPT(2),
    
    /**
     * 角色
     */
    ROLE(3),
    
    /**
     * 权限
     */
    PERMISSION(4),
    
    /**
     * 岗位
     */
    POST(5),
    
    /**
     * 菜单
     */
    MENU(6),
    
    /**
     * 字典
     */
    DICT(7),
    
    /**
     * 参数配置
     */
    CONFIG(8),
    
    /**
     * 登录
     */
    LOGIN(10);
    
    
    private final int code;
    
    Module(int code) {
        this.code = code * 100;
    }
    
    public int code() {
        return code;
    }
    
    
    public static void main(String[] args) {
        ResultCode.Business usernameIllegality = ResultCode.Business.USERNAME_ILLEGALITY;
        System.out.println(JSONUtil.toJsonStr(usernameIllegality));
    }
}
