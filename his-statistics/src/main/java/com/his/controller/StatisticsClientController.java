package com.his.controller;

import com.his.echarts.entity.EchartsData;
import com.his.service.StatisticsClientService;
import com.his.util.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
@RestController
@RequestMapping("/statistics/client/")
public class StatisticsClientController {

    @Autowired
    private StatisticsClientService statisticsClientService;

    @RequestMapping("address")
    public LayuiResult<?> searchAddressByKeyword(String keyword) {
        Set<EchartsData> set = statisticsClientService.searchAddressByKeyword(keyword);
        return set.size() > 1 ?LayuiResult.success(set) : LayuiResult.failed(keyword + "下无数据");
    }


}