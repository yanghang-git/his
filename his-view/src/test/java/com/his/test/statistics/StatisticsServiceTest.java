package com.his.test.statistics;

import com.alibaba.druid.util.lang.Consumer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.his.config.SpringConfig;
import com.his.mapper.ReturnVehicleSmsMapper;
import com.his.pojo.*;
import com.his.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class StatisticsServiceTest {

    @Autowired
    private StatisticsClientService statisticsClientService;

    @Autowired
    private RentOutLogService rentOutLogService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private StatisticsShopService statisticsShopService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ShopService shopService;


    @Autowired
    private JsonMapper mapp;
    @Test
    public void testSearchByAddressKeyword() throws JsonProcessingException {
        LocalDate start = LocalDate.parse("2020-06-01");
        LocalDate end = LocalDate.parse("2022-01-01");
        JsonMapper mapper = new JsonMapper();//JsonMapper.builder().build();
        KpRoleAdmin kpRoleAdmin = new KpRoleAdmin(1, 2);
        String s = mapp.writeValueAsString(kpRoleAdmin);
        System.out.println(s);
    }
}