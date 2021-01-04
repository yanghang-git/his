package com.his.controller;

import com.his.echarts.entity.EchartsData;
import com.his.service.StatisticsClientService;
import com.his.service.StatisticsRentOutLogService;
import com.his.util.LayuiResult;
import com.his.vo.LocalDateInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
@RestController
@RequestMapping("/statistics/rentOutLog/")
public class StatisticsRentOutLogController {

    @Autowired
    private StatisticsRentOutLogService statisticsRentOutLogService;

    @RequestMapping("date")
    public LayuiResult<?> searchAddressByKeyword(String startDate, String endDate) {
        Map<String, Double> map = statisticsRentOutLogService.searchMoneyByOfTheTime(LocalDate.parse(startDate), LocalDate.parse(endDate).plus(1, ChronoUnit.MONTHS));
        return map.size() > 0 ?LayuiResult.success(map) : LayuiResult.failed("无数据");
    }


}