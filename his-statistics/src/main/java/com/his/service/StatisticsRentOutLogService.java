package com.his.service;


import com.his.service.impl.StatisticsRentOutLogServiceImpl.EchartsShop;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
public interface StatisticsRentOutLogService {

    Map<String, Double> searchMoneyByOfTheTime(LocalDate startDate, LocalDate endDate);

    Map<String, Map<String, Integer>> searchVehicleType(LocalDate startDate, LocalDate endDate);

    Map<String, List<Integer>> searchPriceRange(LocalDate startDate, LocalDate endDate);

    Map<String, Map<String, EchartsShop>> statisticsShop(LocalDate startDate, LocalDate endDate);
}
