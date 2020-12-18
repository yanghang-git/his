package com.his.test.authority;

import com.his.config.SpringConfig;
import com.his.mapper.KpPermRoleMapper;
import com.his.mapper.KpPermissionMapper;
import com.his.pojo.KpPermRole;
import com.his.pojo.KpPermission;
import com.his.service.KpPermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-11
 *
 * @author yh
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class KpPermissionServiceTest {
    @Autowired
    private KpPermissionService kpPermissionService;


    @Autowired
    private KpPermRoleMapper kpPermRoleMapper;

    @Test
    public void insertPerm() {
        List<KpPermission> roles = kpPermissionService.list();
        List<KpPermRole> collect = roles.stream().map(item -> new KpPermRole(item.getPermId(), 10)).collect(Collectors.toList());

        System.out.println(collect);

        kpPermRoleMapper.insertBatch(collect);
    }


    @Test
    public void testSearchPermCodeList() {
        System.out.println(kpPermissionService.searchPermCodeList(new Integer[]{1}));
    }

    @Test
    public void testSearchMenuMap() {
        System.out.println(kpPermissionService.searchPermTree());
    }
}