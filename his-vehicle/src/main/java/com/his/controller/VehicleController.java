package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.*;
import com.his.service.VehicleService;
import com.his.util.LayuiResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-26
 *
 * @author yh
 */
@RestController
@RequestMapping("/vehicle/")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;


    @RequestMapping("searchPage")
    public LayuiResult<List<Vehicle>> searchPage(Integer page, Integer limit, @RequestParam(defaultValue = "false") Boolean isAllShop, String licensePlateNumber, String vehicleColor, String vehicleDesc, Integer vehicleType, Double rentOutStart, Double rentOutEnd) {
        Integer shop = isAllShop ? null : ((KpAdmin) SecurityUtils.getSubject().getPrincipal()).getAdminShop();
        Page<Vehicle> list = vehicleService.searchPageByKeyword(page, limit, shop, licensePlateNumber, vehicleColor, vehicleType, vehicleDesc, rentOutStart, rentOutEnd);
        return LayuiResult.success(list);
    }
}