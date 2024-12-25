package com.ruoyi.system.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.ruoyi.common.core.result.Result;
import com.ruoyi.common.core.result.ResultData;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.LogBusinessTypeEnum;
import com.ruoyi.system.domain.bo.SysMenuModifyBO;
import com.ruoyi.system.domain.bo.SysMenuSaveBO;
import com.ruoyi.system.domain.query.SysMenuQuery;
import com.ruoyi.system.domain.vo.SysMenuVo;
import com.ruoyi.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "菜单管理", description = "菜单管理")
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class SysMenuController {
    
    private final SysMenuService sysMenuService;
    
    @Operation(summary = "保存", description = "保存")
    @PostMapping
    @Log(title = "菜单管理", businessType = LogBusinessTypeEnum.SAVE)
    public ResultData save(@RequestBody SysMenuSaveBO param) {
        sysMenuService.save(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "menuId", description = "菜单ID", in = ParameterIn.PATH)})
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{menuId}")
    @Log(title = "菜单管理", businessType = LogBusinessTypeEnum.DELETE)
    public ResultData delete(@PathVariable("menuId") Long menuId) {
        sysMenuService.delete(menuId);
        return ResultData.success();
    }
    
    @Operation(summary = "修改", description = "修改")
    @PutMapping
    @Log(title = "菜单管理", businessType = LogBusinessTypeEnum.MODIFY)
    public ResultData modify(@RequestBody SysMenuModifyBO param) {
        sysMenuService.modify(param);
        return ResultData.success();
    }
    
    @Parameters(value = {@Parameter(name = "menuId", description = "菜单ID", in = ParameterIn.PATH)})
    @Operation(summary = "详情", description = "详情")
    @GetMapping(value = "/{menuId}")
    public Result<SysMenuVo> getInfo(@PathVariable("menuId") Long menuId) {
        return ResultData.success(sysMenuService.get(menuId));
    }
    
    @Operation(summary = "列表", description = "菜单列表")
    @PostMapping("/list")
    public Result<List<SysMenuVo>> list(@RequestBody SysMenuQuery param) {
        List<SysMenuVo> menuList = sysMenuService.list(param, 1L);
        return ResultData.success(menuList);
    }
    
    @Operation(summary = "菜单树", description = "新建角色弹窗使用")
    @GetMapping("/tree")
    public Result<List<Tree<Long>>> tree() {
        List<SysMenuVo> voList = sysMenuService.list(null, 1L);
        List<Tree<Long>> treeList = this.buildTree(voList);
        return ResultData.success(treeList);
    }
    
    
    @Parameters(value = {@Parameter(name = "roleId", description = "角色ID", in = ParameterIn.PATH)})
    @Operation(summary = "根据角色ID查询菜单下拉树结构", description = "根据角色ID查询菜单下拉树结构")
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
