package com.his.controller;

import com.his.echarts.entity.EchartsData;
import com.his.service.StatisticsClientService;
import com.his.service.StatisticsShopService;
import com.his.util.LayuiResult;
import com.his.vo.ShopStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
@RequestMapping("/statistics/shop/")
public class StatisticsShopController {

    @Autowired
    private StatisticsShopService statisticsShopService;

    @RequestMapping("chinaDistributed")
    public LayuiResult<Map<String, Integer>> searchChinaDistributed() {
        return LayuiResult.success(statisticsShopService.searchChinaDistributed());
    }


    @RequestMapping("searchPage")
    public LayuiResult<List<ShopStatisticsVo>> searchPage(String startDate, String endDate) {
        return LayuiResult.success(statisticsShopService.searchPage(
                StringUtils.isEmpty(startDate) ? null : LocalDate.parse(startDate)
                , StringUtils.isEmpty(endDate) ? null : LocalDate.parse(endDate).plusDays(1)
        ));
    }

    @RequestMapping("shopEmployee")
    public LayuiResult<Map<String, List<String>>> searchShopEmployee(Integer shopId) {
        return LayuiResult.success(statisticsShopService.searchShopEmployee(shopId));
    }

}