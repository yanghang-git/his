package com.his.test.statistics;

import com.his.config.SpringConfig;
import com.his.service.RentOutLogService;
import com.his.service.StatisticsClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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