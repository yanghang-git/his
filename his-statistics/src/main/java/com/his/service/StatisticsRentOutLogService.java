package com.his.service;


import com.his.vo.LocalDateInterval;

import java.time.LocalDate;
import java.util.Map;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
public interface StatisticsRentOutLogService {

    Map<String, Double> searchMoneyByOfTheTime(LocalDate startDate, LocalDate endDate);

}
