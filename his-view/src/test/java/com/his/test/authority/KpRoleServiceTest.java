package com.his.test.authority;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.his.config.SpringConfig;
import com.his.exception.OrderFormSaveFailedException;
import com.his.pojo.CallVehicleRecord;
import com.his.pojo.KpRole;
import com.his.pojo.Vehicle;
import com.his.service.CallVehicleRecordService;
import com.his.service.KpRoleService;
import com.his.service.VehicleService;
import com.his.service.impl.CallVehicleRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: KpRoleService的测试类
 * Date: 20-12-10
 *
 * @author yh
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class KpRoleServiceTest {

    @Autowired
    private KpRoleService kpService;


    @Autowired
    private VehicleService vehicleService;


    @Test
    public void test() throws CloneNotSupportedException {

    }

    @Test
    public void testGetRoleIdByUserId() {
        List<Integer> roleIdList = kpService.getRoleIdByUserId(10);
        Integer[] roleIdArray = new Integer[roleIdList.size()];
        roleIdList.toArray(roleIdArray);
        System.out.println(Arrays.toString(roleIdArray));
    }
}