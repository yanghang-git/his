package com.his.controller;

import com.his.pojo.RentOutLog;
import com.his.pojo.Vehicle;
import com.his.service.RentOutLogService1;
import com.his.service.RentOutService1;
import com.his.service.VehicleService;
import com.his.util.LayuiResult;
import com.his.vo.RentOutLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentOutLog/")
public class RentOutLogController1 {

    @Autowired
    private RentOutLogService1 rentOutLogService;

    @Autowired
    private RentOutService1 rentOutService;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping("add")
    public LayuiResult<?> addOrderForm(@RequestBody RentOutLogVo vo) {
        System.out.println(vo.getRentOutLog());
        System.out.println(vo.getLicensePlateNumberList());

        List<RentOutLog> list = null;
        boolean flag = false;
        // list
        try {
            Vehicle vehicle = new Vehicle();
            list = rentOutLogService.addRentOutLogList(vo);
            flag = rentOutLogService.saveBatch(list);
           rentOutService.removeById(vo.getRentOutLog().getLogNumbers());
           vehicleService.changeVehicleState(vo.getLicensePlateNumberList(), true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return flag ? LayuiResult.success("还车成功") : LayuiResult.failed("还车失败");
    }
}
