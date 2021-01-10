package com.his.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.RentOut;
import com.his.pojo.RentOutLog;
import com.his.service.OrderHistoryService;
import com.his.util.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderHistory")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    // 查询 + 分页
    // Request 请求  Mapping  映射
    @RequestMapping("searchPage")
    public LayuiResult<List<RentOutLog>> searchPage(Integer page, Integer limit,String clientName, String logNumbers,String actualReturnTime){

        Page<RentOutLog> rentOutLog = orderHistoryService.select(page, limit, clientName,logNumbers,logNumbers,actualReturnTime);
        System.out.println(rentOutLog.getRecords());
        return LayuiResult.success(rentOutLog);
    }
}
