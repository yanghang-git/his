package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.VehicleType;
import com.his.service.VehicleTypeService;
import com.his.util.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// RestController
// ResponseBody
// Controller
@RestController
@RequestMapping("/vehicle/type/")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    // 查询 + 分页
    // Request 请求  Mapping  映射
    @RequestMapping("searchPage")
    public LayuiResult<List<VehicleType>> searchPage(Integer page, Integer limit, String keyword){
        Page<VehicleType> vehicleTypePage = null;
        try {
            vehicleTypePage = vehicleTypeService.searchPageByKeyword(page, limit, keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LayuiResult.success(vehicleTypePage);
    }

    @RequestMapping("list")
    public LayuiResult<List<VehicleType>> list(){
        return LayuiResult.success(vehicleTypeService.list());
    }

    // 添加
    @RequestMapping("add")
    public LayuiResult<?> add(VehicleType vehicleType){
        boolean flag = vehicleTypeService.save(vehicleType);
        return flag ? LayuiResult.success("添加成功") : LayuiResult.failed("添加失败");
    }

    // 删除
    @RequestMapping("remove")
    public LayuiResult<?> remove(Integer[] ids) {
        boolean flag = vehicleTypeService.removeByIds(Arrays.asList(ids));
        return flag ? LayuiResult.success("删除成功") : LayuiResult.failed("删除失败");
    }

    // 修改
    @RequestMapping("update")
    public LayuiResult<?> update(VehicleType vehicleType){
        boolean flag = vehicleTypeService.updateById(vehicleType);
        return flag ? LayuiResult.success("修改成功") : LayuiResult.failed("修改失败");
    }
}
