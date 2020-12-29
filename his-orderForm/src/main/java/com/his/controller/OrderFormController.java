package com.his.controller;

import com.his.util.LayuiResult;
import com.his.vo.RentOutVo;
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
public class OrderFormController {


    // client,
    //                                    rentOut,
    //                                    rentOutVehicleList,
    //                                    isAdd,
    //                                    isUpdate
    @RequestMapping("test")
    public LayuiResult<?> test(@RequestBody RentOutVo vo) {
        System.out.println(vo);
        return LayuiResult.success("sdf");
    }

    public void test() {
        return;
    }
}