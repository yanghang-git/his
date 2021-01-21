package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.his.pojo.Client;
import com.his.pojo.RentOutLog;
import com.his.pojo.Vehicle;
import com.his.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ClientService clientService;

    @Override
    public Map<String, Double> searchMoneyByOfTheTime(LocalDate startDate, LocalDate endDate) {
        List<RentOutLog> list = rentOutLogService.list(generateDateAndShopNameAndSort(startDate, endDate));
        if(list.isEmpty()) {
            return Collections.emptyMap();
        }
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

    @Override
    public Map<String, Map<String, Integer>> searchVehicleType(LocalDate startDate, LocalDate endDate) {
        List<RentOutLog> list = rentOutLogService.list(generateDateAndShopNameAndSort(startDate, endDate));
        if(list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Map<String, Integer>> map = new LinkedHashMap<>(list.size());
        for (RentOutLog log : list) {
            String key = log.getOfTheTime().toString().substring(0, 7);
            if (!map.containsKey(key)) {
                map.put(key, new HashMap<>());
            }
            Map<String, Integer> empMap = map.get(key);
            empMap.put(log.getVehicleType(), empMap.containsKey(log.getVehicleType()) ? empMap.get(log.getVehicleType()) + 1 : 1);
        }
        return map;
    }

    @Override
    public Map<String, List<Integer>> searchPriceRange(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<RentOutLog> wrapper = (LambdaQueryWrapper<RentOutLog>) generateDateAndShopNameAndSort(startDate, endDate);
        wrapper.ne(RentOutLog::getExistingProblem, "退单");
        List<RentOutLog> list = rentOutLogService.list(wrapper);
        if(list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, List<Integer>> map = new LinkedHashMap<>(list.size());
        Map<String, Double> vehicleRentOutPriceMap = new HashMap<>();
        List<Vehicle> vehicles = vehicleService.listByIds(list.stream().map(RentOutLog::getLicensePlateNumber).collect(Collectors.toSet()));
        vehicles.forEach(vehicle -> vehicleRentOutPriceMap.put(vehicle.getLicensePlateNumber(), vehicle.getRentOut()));

        for (RentOutLog log : list) {
            String key = log.getOfTheTime().toString().substring(0, 7);
            if (!map.containsKey(key)) {
                ArrayList<Integer> internalList = new ArrayList<>(3);
                internalList.add(0);
                internalList.add(0);
                internalList.add(0);
                map.put(key, internalList);
            }
            double money = vehicleRentOutPriceMap.get(log.getLicensePlateNumber());
            List<Integer> empList = map.get(key);
            if (money > 1 && money < 499) {
                empList.set(0, empList.get(0) + 1);
            } else if (money < 1499) {
                empList.set(1, empList.get(1) + 1);
            } else {
                empList.set(2, empList.get(2) + 1);
            }
        }
        return map;
    }

    @Override
    public Map<String, Map<String, EchartsShop>> statisticsShop(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<RentOutLog> logWrapper = new LambdaQueryWrapper<>();
        logWrapper.ne(RentOutLog::getExistingProblem, "退单");
        logWrapper.ge(startDate != null, RentOutLog::getOfTheTime, startDate);
        logWrapper.le(endDate != null, RentOutLog::getOfTheTime, endDate);
        logWrapper.groupBy(RentOutLog::getLogNumbers);
        logWrapper.orderByAsc(RentOutLog::getOfTheTime);
        List<RentOutLog> list = rentOutLogService.list(logWrapper);
        // 1. 出租次数， 累计金额， 新增用户
        // Map<时间， Map<门店名称, {出租次数,累计金额, 新增用户}>>
        Map<String, Map<String, EchartsShop>> map = new LinkedHashMap<>();
        for (RentOutLog log : list) {
            String key = log.getOfTheTime().toString().substring(0, 7);
            if (!map.containsKey(key)) {
                map.put(key, new HashMap<>());
            }
            Map<String, EchartsShop> empMap = map.get(key);
            if (!empMap.containsKey(log.getLogShop())) {
                empMap.put(log.getLogShop(), new EchartsShop());
            }
            EchartsShop echartsShop = empMap.get(log.getLogShop());
            echartsShop.rentOutSize += 1;
            echartsShop.rentOutTotalMoney += log.getActualCollectionMoney() < 0 ? 0 : log.getActualCollectionMoney();
        }
        LambdaQueryWrapper<Client> clientWrapper = new LambdaQueryWrapper<>();
        clientWrapper.ge(startDate != null, Client::getCreateTime, startDate);
        clientWrapper.le(endDate != null, Client::getCreateTime, endDate);
        clientWrapper.orderByAsc(Client::getCreateTime);
        List<Client> clientList = clientService.list(clientWrapper);
        for (Client client : clientList) {
            String key = client.getCreateTime().toString().substring(0, 7);
            if (!map.containsKey(key)) {
                map.put(key, new HashMap<>());
            }
            Map<String, EchartsShop> empMap = map.get(key);
            String shopName = shopService.getShopNameById(client.getRegisterShop());
            if (!empMap.containsKey(shopName)) {
                empMap.put(shopName, new EchartsShop());
            }
            empMap.get(shopName).newClientSize += 1;
        }
        return map;
    }

    private Wrapper<RentOutLog> generateDateAndShopNameAndSort(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<RentOutLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOutLog::getLogShop, shopService.getCurrentShopName());
        wrapper.ge(startDate !=null, RentOutLog::getOfTheTime, startDate);
        wrapper.le(endDate != null, RentOutLog::getOfTheTime, endDate);
        wrapper.orderByAsc(RentOutLog::getOfTheTime);
        return wrapper;
    }


    public static class EchartsShop implements Serializable {
        private static final long serialVersionUID = 6532872456423858123L;
        private Integer rentOutSize = 0;
        private Integer newClientSize = 0;
        private Double rentOutTotalMoney = 0.0;

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public Integer getRentOutSize() {
            return rentOutSize;
        }

        public void setRentOutSize(Integer rentOutSize) {
            this.rentOutSize = rentOutSize;
        }

        public Integer getNewClientSize() {
            return newClientSize;
        }

        public void setNewClientSize(Integer newClientSize) {
            this.newClientSize = newClientSize;
        }

        public Double getRentOutTotalMoney() {
            return rentOutTotalMoney;
        }

        public void setRentOutTotalMoney(Double rentOutTotalMoney) {
            this.rentOutTotalMoney = rentOutTotalMoney;
        }
    }
}