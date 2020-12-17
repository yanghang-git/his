package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.KpAdmin;
import com.his.service.KpAdminService;
import com.his.util.LayuiResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-13
 *
 * @author yh
 */
@RestController
@RequestMapping("/admin/")
public class KpAdminController {
    @Autowired
    private KpAdminService kpAdminService;

    @RequiresPermissions("admin:search")
    @RequestMapping("getPage")
    public LayuiResult<List<KpAdmin>> getPageByUsernameAndRoleId(Integer page, Integer limit, String loginName, String email, String phone) {
        Page<KpAdmin> userPage = kpAdminService.searchPage(page, limit, loginName, email, phone);
        return LayuiResult.success(userPage);
    }

    @RequiresPermissions("admin:add")
    @RequestMapping("save")
    public LayuiResult<?> save(KpAdmin user) {
        boolean flag = kpAdminService.save(user);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequiresPermissions("admin:edit")
    @RequestMapping("edit")
    public LayuiResult<?> edit(KpAdmin user) {
        boolean flag = kpAdminService.updateById(user);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequiresPermissions("admin:remove")
    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer id) {
        boolean flag = kpAdminService.removeById(id);
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }
}