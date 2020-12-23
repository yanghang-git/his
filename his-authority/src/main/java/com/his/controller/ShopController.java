package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.Shop;
import com.his.service.ShopService;
import com.his.util.LayuiResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-18
 *
 * @author yh
 */
@RestController
@RequestMapping("/shop/")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequiresPermissions("admin:search")
    @RequestMapping("/list")
    public LayuiResult<List<Shop>> getList() {
        return LayuiResult.success(shopService.list());
    }



    @RequiresPermissions("shop:search")
    @RequestMapping("getPage")
    public LayuiResult<List<Shop>> getPageByKeyword(Integer page, Integer limit, String keyword) {
        Page<Shop> rolePage = shopService.searchPage(page, limit, keyword);
        return LayuiResult.success(rolePage);
    }

    @RequiresPermissions("shop:add")
    @RequestMapping("save")
    public LayuiResult<?> save(Shop shop) {
        boolean flag = shopService.save(shop);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequiresPermissions("shop:edit")
    @RequestMapping("edit")
    public LayuiResult<?> edit(Shop shop) {
        boolean flag = shopService.updateById(shop);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequiresPermissions("shop:remove")
    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer[] ids) {
        boolean flag = shopService.removeByIds(Arrays.asList(ids));
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }
}