package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.enums.EnableStatusEnum;
import com.ruoyi.common.core.enums.YesOrNoEnum;
import com.ruoyi.common.core.exception.BusinessException;
import com.ruoyi.common.core.result.ResultCode;
import com.ruoyi.common.core.utils.IdUtil;
import com.ruoyi.common.core.utils.ValidatorUtil;
import com.ruoyi.common.satoken.utils.SecurityUtils;
import com.ruoyi.system.domain.bo.SysMenuModifyBO;
import com.ruoyi.system.domain.bo.SysMenuSaveBO;
import com.ruoyi.system.domain.pojo.SysMenu;
import com.ruoyi.system.domain.pojo.SysRole;
import com.ruoyi.system.domain.query.SysMenuQuery;
import com.ruoyi.system.domain.vo.MetaVo;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.domain.vo.SysMenuVo;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Link
 * @date 2024-11-08
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    
    private final SysMenuMapper sysMenuMapper;
    
    private final SysRoleMapper sysRoleMapper;
    
    @Override
    public void save(SysMenuSaveBO param) {
        ValidatorUtil.validate(param);
        
        // 校验菜单名称是否存在
        if (!this.checkUniqueName(param.getMenuName(), param.getMenuId(), param.getParentId())) {
            throw new BusinessException(ResultCode.Business.MENU_NAME_IS_NOT_UNIQUE);
        }
        
        // 校验外链是否已http(s)开头
        if (YesOrNoEnum.YES.getCode().equals(param.getIsFrame()) && !this.isHttpUrl(param.getPath())) {
            throw new BusinessException(ResultCode.Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP);
        }
        SysMenu sysMenRecord = BeanUtil.copyProperties(param, SysMenu.class);
        sysMenRecord.setMenuId(IdUtil.getId());
        sysMenRecord.setStatus(EnableStatusEnum.ENABLE.getCode());
        sysMenRecord.setCreateTime(LocalDateTime.now());
        sysMenRecord.setUpdateTime(LocalDateTime.now());
        int i = sysMenuMapper.insert(sysMenRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.MENU_SAVE_FAIL);
        }
    }
    
    @Override
    public void delete(Long menuId) {
        ValidatorUtil.validate(menuId);
        
        // 校验是否存在子菜单
        if (sysMenuMapper.countChildByMenuId(menuId) > 0) {
            throw new BusinessException(ResultCode.Business.MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE);
        }
        // 校验菜单是否已经分配
        if (sysMenuMapper.countRoleByMenuId(menuId) > 0) {
            throw new BusinessException(ResultCode.Business.MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE);
        }
        int i = sysMenuMapper.deleteById(menuId);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.MENU_DELETE_FAIL);
        }
    }
    
    @Override
    public void modify(SysMenuModifyBO param) {
        ValidatorUtil.validate(param);
        
        // 校验菜单名称是否存在
        if (!this.checkUniqueName(param.getMenuName(), param.getMenuId(), param.getParentId())) {
            throw new BusinessException(ResultCode.Business.MENU_NAME_IS_NOT_UNIQUE);
        }
        // 校验外链是否已http(s)开头
        if (YesOrNoEnum.YES.getCode().equals(param.getIsFrame()) && !this.isHttpUrl(param.getPath())) {
            throw new BusinessException(ResultCode.Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP);
        }
        // 校验父级菜单是否合法
        if (param.getParentId() != 0L) {
            SysMenu parentSysMenuInfo = sysMenuMapper.selectById(param.getParentId());
            if (parentSysMenuInfo == null || !parentSysMenuInfo.getStatus().equals(EnableStatusEnum.ENABLE.getCode())) {
                throw new BusinessException(ResultCode.Business.MENU_PARENT_DEPT_NO_EXIST_OR_DISABLED);
            }
        }
        // 校验上级菜单是否是自己
        if (param.getParentId().equals(param.getMenuId())) {
            throw new BusinessException(ResultCode.Business.MENU_PARENT_ID_NOT_ALLOW_SELF);
        }
        
        SysMenu sysMenuRecord = BeanUtil.copyProperties(param, SysMenu.class);
        sysMenuRecord.setUpdateTime(LocalDateTime.now());
        int i = sysMenuMapper.updateById(sysMenuRecord);
        if (i != 1) {
            throw new BusinessException(ResultCode.Business.MENU_MODIFY_FAIL);
        }
    }
    
    @Override
    public List<SysMenuVo> listTreeByUserId(Long userId) {
        List<SysMenuVo> voList = sysMenuMapper.listMenuTreeAll();
        if (SecurityUtils.isSuper(userId)) {
            voList = sysMenuMapper.listMenuTreeAll();
        } else {
            // TODO 根据用户ID查询菜单列表
        }
        
        // 构建菜单树
        return getChildPerms(voList, 0);
    }
    
    @Override
    public List<RouterVo> build(List<SysMenuVo> param) {
        
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuVo menu : param) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
            List<SysMenuVo> cMenuList = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenuList) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(build(cMenuList));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }
    
    @Override
    public List<SysMenuVo> list(SysMenuQuery param, Long userId) {
        List<SysMenuVo> voList = sysMenuMapper.listAll(param);
        if (SecurityUtils.isSuper(userId)) {
            voList = sysMenuMapper.listAll(param);
        } else {
            // TODO 根据用户查询系统菜单列表
        }
        return voList;
    }
    
    @Override
    public SysMenuVo get(Long menuId) {
        ValidatorUtil.validate(menuId);
        
        SysMenu sysMenu = sysMenuMapper.selectById(menuId);
        SysMenuVo vo = BeanUtil.copyProperties(sysMenu, SysMenuVo.class);
        
        return vo;
    }
    
    @Override
    public Boolean checkUniqueName(String menuName, Long menuId, Long parentMenuId) {
        ValidatorUtil.validate(menuName, parentMenuId);
        
        menuId = menuId == null ? -1L : menuId;
        SysMenuVo sysMenuInfo = sysMenuMapper.getByMenuNameAndParentId(menuName, parentMenuId);
        if (sysMenuInfo != null && !menuId.equals(sysMenuInfo.getMenuId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    @Override
    public List<Long> listMenuByRoleId(Long roleId) {
        SysRole role = sysRoleMapper.selectById(roleId);
        return sysMenuMapper.listMenuByRoleId(roleId, role.getMenuCheckStrictly());
    }
    
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuVo> getChildPerms(List<SysMenuVo> list, int parentId) {
        List<SysMenuVo> returnList = new ArrayList<SysMenuVo>();
        for (SysMenuVo t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }
    
    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t    子节点
     */
    private void recursionFn(List<SysMenuVo> list, SysMenuVo t) {
        // 得到子节点列表
        List<SysMenuVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVo tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }
    
    /**
     * 得到子节点列表
     */
    private List<SysMenuVo> getChildList(List<SysMenuVo> list, SysMenuVo t) {
        List<SysMenuVo> tlist = new ArrayList<SysMenuVo>();
        for (SysMenuVo n : list) {
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }
    
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVo> list, SysMenuVo t) {
        return !getChildList(list, t).isEmpty();
    }
    
    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    private String getRouteName(SysMenuVo menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }
    
    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isMenuFrame(SysMenuVo menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && !menu.getIsFrame();
    }
    
    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(SysMenuVo menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && !menu.getIsFrame()) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }
    
    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    private String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[] {Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":"},
                new String[] {"", "", "", "/", "/"});
    }
    
    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isInnerLink(SysMenuVo menu) {
        return !menu.getIsFrame() && HttpUtil.isHttp(menu.getPath());
    }
    
    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    private String getComponent(SysMenuVo menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(
                menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }
    
    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isParentView(SysMenuVo menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }
    
    private boolean isHttpUrl(String path) {
        boolean http = HttpUtil.isHttp(path);
        ;
        boolean https = HttpUtil.isHttps(path);
        return http || https;
    }
    
}
