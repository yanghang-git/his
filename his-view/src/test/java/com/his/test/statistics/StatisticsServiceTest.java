package com.his.test.statistics;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.his.config.SpringConfig;
import com.his.echarts.entity.EchartsData;
import com.his.pojo.RentOut;
import com.his.pojo.RentOutLog;
import com.his.service.RentOutLogService;
import com.his.service.StatisticsClientService;
import com.his.service.StatisticsRentOutLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.function.Predicate;

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

    @Test
    public void testSearchByAddressKeyword() {


    }
}