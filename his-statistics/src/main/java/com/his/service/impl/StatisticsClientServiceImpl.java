package com.his.service.impl;

import com.his.echarts.entity.EchartsData;
import com.his.mapper.StatisticsClientMapper;
import com.his.pojo.Client;
import com.his.service.StatisticsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
@Service
public class StatisticsClientServiceImpl implements StatisticsClientService {


    @Autowired
    private StatisticsClientMapper statisticsClientMapper;

    @Override
    public Set<EchartsData> searchAddressByKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return searchProvince(statisticsClientMapper.clientList());
        }
        return searchByKeyword(statisticsClientMapper.clientList(), keyword);
    }

    private Set<EchartsData> searchByKeyword(List<Client> clientList, String keyword) {
        final String endStr = keyword.contains("市") ? "区" : "市";
        return clientList.stream()
                // 筛选出带省和直辖市的数据
                .filter(client -> client.getClientAddress().contains(keyword) && client.getClientAddress().contains(endStr))
                .map(client -> {
                    EchartsData result = new EchartsData();
                    result.setName(client.getClientAddress().substring(0, client.getClientAddress().indexOf(endStr) + endStr.length()));
                    for (Client item : clientList) {
                        if (item.getClientAddress().contains(result.getName())) {
                            result.valuePlusOne();
                        }
                    }
                    return result;
                })
                .collect(Collectors.toSet());
    }


    private Set<EchartsData> searchProvince(List<Client> clientList) {
        final String province = "省";
        final String municipality = "北京市";
        return clientList.stream()
                // 筛选出带省和直辖市的数据
                .filter(client -> client.getClientAddress().contains(province) || client.getClientAddress().contains(municipality))
                .map(client -> {
                    EchartsData result = new EchartsData();
                    if (client.getClientAddress().contains(province)) {
                        result.setName(client.getClientAddress().substring(0, client.getClientAddress().indexOf(province) + province.length()));
                    } else {
                        result.setName(client.getClientAddress().substring(0, client.getClientAddress().indexOf(municipality) + municipality.length()));
                    }
                    for (Client item : clientList) {
                        if (item.getClientAddress().contains(result.getName())) {
                            result.valuePlusOne();
                        }
                    }
                    return result;
                })
                .collect(Collectors.toSet());
    }
}