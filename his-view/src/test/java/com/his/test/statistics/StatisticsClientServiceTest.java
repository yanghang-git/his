package com.his.test.statistics;

import com.his.config.SpringConfig;
import com.his.echarts.entity.EchartsData;
import com.his.service.StatisticsClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class StatisticsClientServiceTest {

    @Autowired
    private StatisticsClientService statisticsClientService;

    @Test
    public void testSearchByAddressKeyword() {
        Set<EchartsData> list = statisticsClientService.searchAddressByKeyword("河北省邯郸市");
        for (EchartsData client : list) {
            System.out.println();
            System.out.println(client);
            System.out.println();
        }
    }
}