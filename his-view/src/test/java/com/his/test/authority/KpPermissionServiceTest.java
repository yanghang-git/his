package com.his.test.authority;

import com.his.config.SpringConfig;
import com.his.mapper.KpPermRoleMapper;
import com.his.mapper.KpPermissionMapper;
import com.his.pojo.KpPermRole;
import com.his.pojo.KpPermission;
import com.his.service.KpPermissionService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        kpPermRoleMapper.insertBatch(collect);
    }


    @Test
    public void testSearchPermCodeList() {
        System.out.println(kpPermissionService.searchPermCodeList(new Integer[]{1}));
    }

    @Test
    public void testSearchMenuMap() {
        System.out.println(new Md5Hash(null, null, 1));
        System.out.println(kpPermissionService.searchPermTree());
    }

    @Test
    public void testList() {

        /*
            1. 获取全部数据
            2. 挑选name 比如 shop  client  admin  role permission 组成一个list
            3. 遍历 全部数据, 获取其中每个shop的数量。 进行相加 最后输出
         */
        List<KpPermission> list = kpPermissionService.list();
        Map<String, Integer> collect = list.stream()
                .filter(item -> item.getPermCode() != null && item.getPermCode().length() > 0 && item.getPermCode().contains("role:"))
                .map(item -> item.getPermCode().substring(0, item.getPermCode().indexOf("role:") + 1))
                .collect(Collectors.toMap(key -> key, value -> {
                    int count = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPermCode().contains(value)) {
                            count++;
                        }
                    }
                    return count;
                }));

        for (Map.Entry<String, Integer> item : collect.entrySet()) {
            System.out.println(item);
        }
    }
}