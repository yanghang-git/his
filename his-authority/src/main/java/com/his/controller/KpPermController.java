package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.KpPermission;
import com.his.pojo.KpRole;
import com.his.service.KpPermissionService;
import com.his.service.KpRoleService;
import com.his.util.LayuiResult;
import com.his.util.LayuiTreeData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-13
 *
 * @author yh
 */
@RestController
@RequestMapping("/perm/")
public class KpPermController {
    @Autowired
    private KpPermissionService kpPermissionService;

    @RequiresPermissions("permission:search")
    @RequestMapping("getPage")
    public LayuiResult<List<KpPermission>> getPageBynameAndRoleId(Integer page, Integer limit, String keyword) {
        Page<KpPermission> rolePage = kpPermissionService.searchPage(page, limit, keyword);
        return LayuiResult.success(rolePage);
    }

    @RequiresPermissions("permission:add")
    @RequestMapping("save")
    public LayuiResult<?> save(KpPermission permission) {
        boolean flag = kpPermissionService.save(permission);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequiresPermissions("permission:edit")
    @RequestMapping("edit")
    public LayuiResult<?> edit(KpPermission permission) {
        boolean flag = kpPermissionService.updateById(permission);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequiresPermissions("permission:remove")
    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer[] ids) {
        boolean flag = kpPermissionService.removeByIds(Arrays.asList(ids));
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }

    @RequiresPermissions("permission:search")
    @RequestMapping("searchPermTreeByRoleId")
    public LayuiResult<List<LayuiTreeData>> searchPermTreeByRoleId(Integer roleId) {
        return LayuiResult.success(kpPermissionService.searchPermTree(roleId));
    }

    @RequiresPermissions("permission:search")
    @RequestMapping("searchPermTree")
    public LayuiResult<List<LayuiTreeData>> searchPermTree() {
        LayuiTreeData data = new LayuiTreeData();
        data.setChildren(kpPermissionService.searchPermTree());
        data.setTitle("根目录(页面不显示)");
        data.setSpread(true);
        return LayuiResult.success(Collections.singletonList(data));
    }

    @RequiresPermissions("permission:search")
    @RequestMapping("searchPermMenu")
    public LayuiResult<List<KpPermission>> searchPermMenu() {
        List<KpPermission> list = kpPermissionService.searchPermMenu();
        return LayuiResult.success(list);
    }
}