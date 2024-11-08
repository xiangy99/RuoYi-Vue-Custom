package com.ruoyi.system.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.system.domain.bo.SysMenuModifyBO;
import com.ruoyi.system.domain.bo.SysMenuSaveBO;
import com.ruoyi.system.domain.query.SysMenuQuery;
import com.ruoyi.system.domain.vo.SysMenuVo;
import com.ruoyi.system.service.SysMenuService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * $SysMenuController
 *
 * @author coriander
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    
    private final SysMenuService sysMenuService;
    
    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }
    
    @PostMapping
    //    @Log(title = "菜单管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysMenuSaveBO param) {
        sysMenuService.save(param);
        return ResultData.success();
    }
    
    @DeleteMapping("/{menuId}")
    //    @Log(title = "菜单管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("menuId") Long menuId) {
        sysMenuService.delete(menuId);
        return ResultData.success();
    }
    
    @PutMapping
    //    @Log(title = "菜单管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysMenuModifyBO param) {
        sysMenuService.modify(param);
        return ResultData.success();
    }
    
    @GetMapping(value = "/{menuId}")
    public Result<SysMenuVo> getInfo(@PathVariable("menuId") Long menuId) {
        return ResultData.success(sysMenuService.get(menuId));
    }
    
    @GetMapping("/list")
    public Result<List<SysMenuVo>> list(SysMenuQuery param) {
        List<SysMenuVo> menuList = sysMenuService.list(param, 1L);
        return ResultData.success(menuList);
    }
    
    @GetMapping("/tree")
    public Result<List<Tree<Long>>> tree(SysMenuQuery param) {
        List<SysMenuVo> voList = sysMenuService.list(param, 1L);
        List<Tree<Long>> treeList = this.buildTree(voList);
        return ResultData.success(treeList);
    }
    
    
    @GetMapping(value = "/tree/{roleId}")
    public Result<HashMap<String, Object>> tree(@PathVariable("roleId") Long roleId) {
        List<SysMenuVo> voList = sysMenuService.list(new SysMenuQuery(), 1L);
        List<Tree<Long>> treeList = this.buildTree(voList);
        List<Long> listMenuByRoleId = sysMenuService.listMenuByRoleId(roleId);
        HashMap<String, Object> voMap = new HashMap<>();
        voMap.put("checkedKeys", listMenuByRoleId);
        voMap.put("menuList", treeList);
        return ResultData.success(voMap);
    }
    
    private List<Tree<Long>> buildTree(List<SysMenuVo> voList) {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        return TreeUtil.build(voList, 0L, treeNodeConfig,
                (menuVo, tree) -> tree.setId(menuVo.getMenuId()).setParentId(menuVo.getParentId())
                        .setWeight(menuVo.getSort()).putExtra("label", menuVo.getMenuName()));
    }
    
}
