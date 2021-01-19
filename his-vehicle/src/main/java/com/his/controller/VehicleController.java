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

import java.awt.geom.QuadCurve2D;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    @RequestMapping("getPage")
    public LayuiResult<List<Vehicle>> list(Integer page, Integer limit, String plateNumber, Integer type, String color, String desc, Boolean outState) {
        Page<Vehicle> vehiclePage = vehicleService.searchPage(page, limit, plateNumber, type, color, desc, outState);
        return LayuiResult.success(vehiclePage);
    }
    @RequestMapping("list1")
    public LayuiResult<List<Vehicle>> list() {
        return LayuiResult.success(vehicleService.list());
    }
    @RequestMapping("list")
    public List<Vehicle> list1() {
        return vehicleService.list();
    }
    @RequestMapping("add")
    public LayuiResult<?> add(Vehicle vehicle){
        vehicle.setTime(LocalDateTime.now());
        vehicle.setVehicleState(true);
        List<Vehicle> vehiclePage = list1();
        for (Vehicle v: vehiclePage) {
            if(vehicle.getLicensePlateNumber().equals(v.getLicensePlateNumber()) || vehicle.getVehicleIdNumber().equals(v.getVehicleIdNumber())){
                return LayuiResult.failed("此车已存在！！！");
            }
        }
        boolean flag = false;
        try {
            flag = vehicleService.save(vehicle);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }
    // 修改
    @RequestMapping("update")
    public LayuiResult<?> update(Vehicle vehicle){
        boolean flag = vehicleService.updateById(vehicle);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }
    // 删除
    @RequestMapping("remove")
    public LayuiResult<?> remove(String[] ids) {
        boolean flag = false;
        try {
            List<Vehicle> vehiclePage = list1();
            for (Vehicle v: vehiclePage) {
                System.out.println("车牌号:"+v.getLicensePlateNumber());
                System.out.println("list:"+Arrays.asList(ids).get(0));
                System.out.println("判断是否相等:"+Arrays.asList(ids).get(0).equals(v.getLicensePlateNumber()));
                for(String i : Arrays.asList(ids))
                if(i.equals(v.getLicensePlateNumber())){
                    if(v.getVehicleState() == false){
                        return LayuiResult.failed("该车已经出租,不能出租！！！");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            flag = vehicleService.removeByIds(Arrays.asList(ids));
            return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");

    }
}