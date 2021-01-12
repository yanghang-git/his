package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.his.pojo.RentOutLog;
import com.his.service.RentOutLogService;
import com.his.service.ShopService;
import com.his.service.StatisticsRentOutLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: TO DO
 * Date: 21-1-2
 *
 * @author yh
 */
@Service
public class StatisticsRentOutLogServiceImpl implements StatisticsRentOutLogService {
    @Autowired
    private RentOutLogService rentOutLogService;


    @Autowired
    private ShopService shopService;

    @Override
    public Map<String, Double> searchMoneyByOfTheTime(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<RentOutLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOutLog::getLogShop, shopService.getCurrentShopName());
        wrapper.ge(startDate !=null ,RentOutLog::getOfTheTime, startDate);
        wrapper.lt(endDate !=null ,RentOutLog::getOfTheTime, endDate);
        wrapper.orderByAsc(RentOutLog::getOfTheTime);
        List<RentOutLog> list = rentOutLogService.list(wrapper);
        Map<String, Double> map = new LinkedHashMap<>();
        for (RentOutLog rentOutLog : list) {
            String key = rentOutLog.getOfTheTime().toString().substring(0, 7);
            Double value = rentOutLog.getActualCollectionMoney();
            if (map.containsKey(key)) {
                value += map.get(key);
            }
            map.put(key, value);
        }
        return map;
    }
}