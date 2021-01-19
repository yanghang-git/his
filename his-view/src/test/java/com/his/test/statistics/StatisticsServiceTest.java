package com.his.test.statistics;

import com.his.config.SpringConfig;
import com.his.mapper.ReturnVehicleSmsMapper;
import com.his.pojo.ReturnVehicleSms;
import com.his.service.RentOutLogService1;
import com.his.service.StatisticsClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private RentOutLogService1 rentOutLogService;

    @Autowired
    private ReturnVehicleSmsMapper returnVehicleSmsMapper;

    @Test
    public void testSearchByAddressKeyword() {
        returnVehicleSmsMapper.delete(null);
        for (int i = 1; i <= 10; i++) {
            LocalDateTime time = LocalDateTime.now().plusSeconds(10).plusSeconds(i * 10);
            returnVehicleSmsMapper.insert(new ReturnVehicleSms("1"+i, "王"+i, "ShopName", "ShopAddress", "#DSdsgf", time.format(DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy")), time));
        }
        System.out.println(returnVehicleSmsMapper.selectList(null));
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}