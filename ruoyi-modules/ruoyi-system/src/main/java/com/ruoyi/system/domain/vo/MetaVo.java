package com.ruoyi.system.domain.vo;


import cn.hutool.http.HttpUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 路由显示信息
 */
@Getter
@Setter
public class MetaVo {
    
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;
    
    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;
    
    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;
    
    /**
     * 内链地址（http(s)://开头）
     */
    private String link;
    
    public MetaVo() {
    }
    
    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
    
    public MetaVo(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }
    
    public MetaVo(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }
    
    public MetaVo(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        
        if (HttpUtil.isHttp(link)) {
            this.link = link;
        }
    }
}
