package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.KpUser;
import com.his.service.KpUserService;
import com.his.util.LayuiResult;
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
@RequestMapping("/user/")
public class KpUserController {
    @Autowired
    private KpUserService kpUserService;

    @RequestMapping("getPage")
    public LayuiResult<List<KpUser>> getPageByUsernameAndRoleId(Integer page, Integer limit, String loginName, String email, String phone) {
        Page<KpUser> userPage = kpUserService.searchPage(page, limit, loginName, email, phone);
        return LayuiResult.success(userPage);
    }

    @RequestMapping("save")
    public LayuiResult<?> save(KpUser user) {
        boolean flag = kpUserService.save(user);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequestMapping("edit")
    public LayuiResult<?> edit(KpUser user) {
        boolean flag = kpUserService.updateById(user);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer id) {
        boolean flag = kpUserService.removeById(id);
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }
}