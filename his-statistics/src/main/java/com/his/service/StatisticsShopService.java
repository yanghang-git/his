package com.his.service;

import com.his.echarts.entity.EchartsData;
import com.his.vo.ShopStatisticsVo;

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
public interface StatisticsShopService {
    Map<String, Integer> searchChinaDistributed();

    List<ShopStatisticsVo> searchPage(LocalDate startDate, LocalDate endDate);

    Map<String, List<String>> searchShopEmployee(Integer shopId);
}
