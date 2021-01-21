package com.his.controller;

import com.his.service.StatisticsRentOutLogService;
import com.his.service.impl.StatisticsRentOutLogServiceImpl.EchartsShop;
import com.his.util.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    @RequestMapping("marketing")
    public LayuiResult<?> searchMoneyByOfTheTime(String startDate, String endDate) {
        Map<String, Double> map = statisticsRentOutLogService.searchMoneyByOfTheTime(
                StringUtils.isEmpty(startDate) ? null : LocalDate.parse(startDate)
                , StringUtils.isEmpty(endDate) ? null : LocalDate.parse(endDate).plus(1, ChronoUnit.MONTHS)
        );
        return map.size() > 0 ?LayuiResult.success(map) : LayuiResult.failed("无数据");
    }

    @RequestMapping("vehicleType")
    public LayuiResult<?> searchVehicleType(String startDate, String endDate) {
        Map<String, Map<String, Integer>> map = statisticsRentOutLogService.searchVehicleType(
                StringUtils.isEmpty(startDate) ? null : LocalDate.parse(startDate)
                , StringUtils.isEmpty(endDate) ? null : LocalDate.parse(endDate).plus(1, ChronoUnit.MONTHS)
        );
        return map.size() > 0 ?LayuiResult.success(map) : LayuiResult.failed("无数据");
    }

    @RequestMapping("priceRange")
    public LayuiResult<?> searchPriceRange(String startDate, String endDate) {
        Map<String, List<Integer>> map = statisticsRentOutLogService.searchPriceRange(
                StringUtils.isEmpty(startDate) ? null : LocalDate.parse(startDate)
                , StringUtils.isEmpty(endDate) ? null : LocalDate.parse(endDate).plus(1, ChronoUnit.MONTHS)
        );
        return map.size() > 0 ?LayuiResult.success(map) : LayuiResult.failed("无数据");
    }

    @RequestMapping("statisticsShop")
    public LayuiResult<?> statisticsShop(String startDate, String endDate) {
        Map<String, Map<String, EchartsShop>> map = statisticsRentOutLogService.statisticsShop(
                StringUtils.isEmpty(startDate) ? null : LocalDate.parse(startDate)
                , StringUtils.isEmpty(endDate) ? null : LocalDate.parse(endDate).plus(1, ChronoUnit.MONTHS)
        );
        return map.size() > 0 ?LayuiResult.success(map) : LayuiResult.failed("无数据");
    }

}