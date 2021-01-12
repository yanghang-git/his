package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.exception.OrderFormSaveFailedException;
import com.his.pojo.RentOut;
import com.his.pojo.RentOutVehicle;
import com.his.pojo.Vehicle;
import com.his.service.OrderFormService;
import com.his.util.LayuiResult;
import com.his.vo.RentOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
@RestController
@RequestMapping("/orderForm/")
public class OrderFormController {

    @Autowired
    private OrderFormService orderFormService;


    @RequestMapping("addOrderForm")
    public LayuiResult<?> addOrderForm(@RequestBody RentOutVo vo) {
        Boolean flag = orderFormService.addRentOutOrderForm(vo);
        return flag ? LayuiResult.success("成功") : LayuiResult.failed("失败");
    }

    @RequestMapping("searchRentOutVehicle")
    public LayuiResult<List<RentOutVehicle>> searchRentOutVehicle(String oddNumbers) {
        return LayuiResult.success(orderFormService.searchRentOutVehicleByRentOutOddNumbers(oddNumbers));
    }

    @RequestMapping("searchVehicleDesc")
    public LayuiResult<?> searchVehicleDesc(String vehiclePlateNumber) {
        return LayuiResult.success(orderFormService.searchVehicleDescByPrimaryKey(vehiclePlateNumber));
    }


    // 查询 + 分页
    // Request 请求  Mapping  映射
    @RequestMapping("searchPage")
    public LayuiResult<List<RentOut>> searchPage(Integer page, Integer limit, String clientName, String oddNumbers , String ofTheTimeStart, String ofTheTimeEnd){
        Page<RentOut> rentOut = orderFormService.select(page, limit, clientName, oddNumbers, ofTheTimeStart, ofTheTimeEnd);
        return LayuiResult.success(rentOut);
    }

    // 删除
    @RequestMapping("cancel")
    public LayuiResult<?> cancelOrderForm(RentOut rentOut) {
        boolean flag = orderFormService.cancelOrderForm(rentOut);
        return flag ? LayuiResult.success("取消成功") : LayuiResult.failed("取消失败");
    }

    @RequestMapping("outVehicle")
    public LayuiResult<?> outVehicle(String oddNumbers) {
        boolean flag = orderFormService.outVehicle(oddNumbers);
        Double totalRentOutMoney = orderFormService.getTotalRentOutMoneyByOddNumbers(oddNumbers);
        return flag ? LayuiResult.success("出车成功，总租金" + totalRentOutMoney + "元。请收款" + (totalRentOutMoney - 500) + "元，已收押金500元。") : LayuiResult.failed("出车失败");
    }


    @RequestMapping("searchVehicle")
    public LayuiResult<List<Vehicle>> getVehicleList(String oddNumbers) {
        return LayuiResult.success(orderFormService.getVehicleListByOddNumbers(oddNumbers));
    }


    @ExceptionHandler(OrderFormSaveFailedException.class)
    public LayuiResult<?> orderFormSaveFailedExceptionHandler(OrderFormSaveFailedException ex) {
        return LayuiResult.failed(ex.getMessage());
    }



}