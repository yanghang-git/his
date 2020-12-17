package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.KpRole;
import com.his.service.KpRoleService;
import com.his.util.LayuiResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-13
 *
 * @author yh
 */
@RestController
@RequestMapping("/role/")
public class KpRoleController {
    @Autowired
    private KpRoleService kproleService;

    @RequiresPermissions("role:search")
    @RequestMapping("getPage")
    public LayuiResult<List<KpRole>> getPageByKeyword(Integer page, Integer limit, String keyword) {
        Page<KpRole> rolePage = kproleService.searchPage(page, limit, keyword);
        return LayuiResult.success(rolePage);
    }

    @RequiresPermissions("role:add")
    @RequestMapping("save")
    public LayuiResult<?> save(KpRole role) {
        boolean flag = kproleService.save(role);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequiresPermissions("role:edit")
    @RequestMapping("edit")
    public LayuiResult<?> edit(KpRole role) {
        boolean flag = kproleService.updateById(role);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequiresPermissions("role:remove")
    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer[] ids) {
        boolean flag = kproleService.removeByIds(Arrays.asList(ids));
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }

    @RequiresPermissions("role:changePermission")
    @RequestMapping("changePerm")
    public LayuiResult<?> changeAuthByRoleId(Integer roleId, Integer[] permIds) {
        Boolean flag = kproleService.changePermByRoleId(roleId, permIds);
        return flag ? LayuiResult.success("授权成功") : LayuiResult.failed("授权失败");
    }
}