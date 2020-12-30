package com.his.controller;

import com.his.exception.OrderFormSaveFailedException;
import com.his.service.OrderFormService;
import com.his.util.LayuiResult;
import com.his.vo.RentOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("test")
    public LayuiResult<?> test(@RequestBody RentOutVo vo) {
        Boolean flag = orderFormService.addRentOutOrderForm(vo);
        return flag ? LayuiResult.success("成功") : LayuiResult.failed("失败");
    }

    @ExceptionHandler(OrderFormSaveFailedException.class)
    public LayuiResult<?> orderFormSaveFailedExceptionHandler(OrderFormSaveFailedException ex) {
        return LayuiResult.failed(ex.getMessage());
    }

}