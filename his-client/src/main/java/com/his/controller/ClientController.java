package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.Client;
import com.his.service.ClientService;
import com.his.util.LayuiResult;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-21
 *
 * @author yh
 */
@RestController
@RequestMapping("/client/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequiresPermissions("client:search")
    @RequestMapping("getPage")
    public LayuiResult<List<Client>> getPage(Integer page, Integer limit, String clientIdNumber, String clientName, String clientPhone, String clientAddress, Boolean clientSex) {
        Page<Client> userPage = clientService.searchPage(page, limit, clientIdNumber, clientName, clientPhone, clientAddress, clientSex);
        return LayuiResult.success(userPage);
    }

    @RequiresPermissions("client:add")
    @RequestMapping("save")
    public LayuiResult<?> save(Client client) {
        if (client.getCreateTime() == null) {
            client.setCreateTime(LocalDateTime.now());
        }
        boolean flag = clientService.save(client);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    @RequiresPermissions("client:edit")
    @RequestMapping("edit")
    public LayuiResult<?> edit(Client client) {
        boolean flag = clientService.updateById(client);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }

    @RequiresPermissions("client:remove")
    @RequestMapping("remove")
    public LayuiResult<?> remove(String[] ids) {
        // 删除之前需要判断客户当前是否有正在租借的车辆
        boolean flag = clientService.removeByIds(Arrays.asList(ids));
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }

    @ExceptionHandler(value = {MySQLIntegrityConstraintViolationException.class})
    public LayuiResult<?> MySQLIntegrityConstraintViolationExceptionHandler() {
        return LayuiResult.failed("身份证已存在");
    }
}