package com.his.test.authority;

import com.his.config.SpringConfig;
import com.his.service.KpRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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


    @Test
    public void test() {
        System.out.println(kpService.list());
    }

    @Test
    public void testGetRoleIdByUserId() {
        List<Integer> roleIdList = kpService.getRoleIdByUserId(10);
        Integer[] roleIdArray = new Integer[roleIdList.size()];
        roleIdList.toArray(roleIdArray);
        System.out.println(Arrays.toString(roleIdArray));
    }
}