package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.KpAdmin;
import com.his.service.KpAdminService;
import com.his.util.LayuiResult;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/admin/")
public class KpAdminController {
    @Autowired
    private KpAdminService kpAdminService;

    @RequiresPermissions("admin:search")
    @RequestMapping("getPage")
    public LayuiResult<List<KpAdmin>> getPageByUsernameAndRoleId(Integer page, Integer limit, @RequestParam(defaultValue = "-1") Integer adminShop, String loginName, String phone) {
        Page<KpAdmin> userPage = kpAdminService.searchPage(page, limit, adminShop, loginName, phone);
        return LayuiResult.success(userPage);
    }

    @RequiresPermissions("admin:add")
    @RequestMapping("save")
    public LayuiResult<?> save(KpAdmin admin) {
        admin.setAdminPass(new Md5Hash(admin.getAdminPass(), null , 1).toString());
        boolean flag = kpAdminService.save(admin);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequiresPermissions("admin:edit")
    @RequestMapping("edit")
    public LayuiResult<?> edit(KpAdmin admin) {
        boolean flag = kpAdminService.editKpAdminNotNullFeildById(admin);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequiresPermissions("admin:remove")
    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        KpAdmin admin = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        if (list.contains(admin.getAdminId())) {
            return LayuiResult.failed("无法删除现在正在登入的用户");
        }
        boolean flag = kpAdminService.removeByIds(list);
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }


    @RequiresPermissions("admin:changePermission")
    @RequestMapping("changePermission")
    public LayuiResult<?> changePermission(Integer adminId, Integer[] roleIds) {
        Boolean flag = kpAdminService.changeRoleByAdminId(adminId, roleIds);
        return flag ? LayuiResult.success("授权成功") : LayuiResult.failed("授权失败");
    }

    @ExceptionHandler(value = {MySQLIntegrityConstraintViolationException.class})
    public LayuiResult<?> MySQLIntegrityConstraintViolationExceptionHandler() {
        return LayuiResult.failed("登录帐号已存在");
    }
}