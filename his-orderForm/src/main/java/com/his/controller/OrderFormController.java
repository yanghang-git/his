package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.exception.OrderFormSaveFailedException;
import com.his.pojo.RentOut;
import com.his.pojo.VehicleType;
import com.his.service.OrderFormService;
import com.his.service.VehicleTypeService;
import com.his.util.LayuiResult;
import com.his.vo.RentOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    @ExceptionHandler(OrderFormSaveFailedException.class)
    public LayuiResult<?> orderFormSaveFailedExceptionHandler(OrderFormSaveFailedException ex) {
        return LayuiResult.failed(ex.getMessage());
    }


    // 查询 + 分页
    // Request 请求  Mapping  映射
    @RequestMapping("searchPage")
    public LayuiResult<List<RentOut>> searchPage(Integer page, Integer limit, String clientName, String oddNumbers , String ofTheTimeStart, String ofTheTimeEnd){
        Page<RentOut> rentOut = orderFormService.select(page, limit, clientName, oddNumbers, ofTheTimeStart, ofTheTimeEnd);
        return LayuiResult.success(rentOut);
    }

    // 删除
    @RequestMapping("remove")
    public LayuiResult<?> remove(RentOut rentOut) {

        boolean flag = orderFormService.cancelOrderForm(rentOut);
        return flag ? LayuiResult.success("取消成功") : LayuiResult.failed("取消失败");
    }

    // 修改
    @RequestMapping("update")
    public LayuiResult<?> update(RentOut rentOut){

        boolean flag = orderFormService.update(rentOut);
        return flag ? LayuiResult.success("出车成功") : LayuiResult.failed("出车异常");
    }


}