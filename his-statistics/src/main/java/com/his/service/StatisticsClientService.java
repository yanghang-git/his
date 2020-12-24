package com.his.service;

import com.his.echarts.entity.EchartsData;

import java.util.Set;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
public interface StatisticsClientService {
    Set<EchartsData> searchAddressByKeyword(String keyword);
}
