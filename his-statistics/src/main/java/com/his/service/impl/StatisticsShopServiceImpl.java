package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.his.pojo.KpAdmin;
import com.his.pojo.KpRole;
import com.his.pojo.RentOutLog;
import com.his.pojo.Shop;
import com.his.service.*;
import com.his.vo.ShopStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 21-1-19
 *
 * @author yh
 */
@Service
public class StatisticsShopServiceImpl implements StatisticsShopService {
    @Autowired
    private ShopService shopService;

    @Autowired
    private RentOutLogService rentOutLogService;

    @Autowired
    private KpAdminService kpAdminService;

    @Autowired
    private KpRoleService kpRoleService;

    @Autowired
    private ClientService clientService;

    @Override
    public Map<String, Integer> searchChinaDistributed() {
        List<Shop> shopList = shopService.list();
        Map<String, Integer> result = new HashMap<>(shopList.size());
        for (Shop item : shopList) {
            int endIndex = item.getShopAddress().indexOf("省");
            endIndex = endIndex == -1 ? item.getShopAddress().indexOf("市") : endIndex;
            String name = item.getShopAddress().substring(0, endIndex);
            result.put(name, result.containsKey(name) ? result.get(name) + 1 : 1);
        }
        return result;
    }

    @Override
    public List<ShopStatisticsVo> searchPage(LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<RentOutLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(RentOutLog::getExistingProblem, "退单");
        wrapper.ge(startDate !=null, RentOutLog::getOfTheTime, startDate);
        wrapper.le(endDate != null, RentOutLog::getOfTheTime, endDate);
        wrapper.groupBy(RentOutLog::getLogNumbers);
        List<RentOutLog> logList = rentOutLogService.list(wrapper);
        List<Shop> shopList = shopService.list();
        List<ShopStatisticsVo> vo = new ArrayList<>(shopList.size());
        for (Shop shop : shopList) {
            ShopStatisticsVo empVo = new ShopStatisticsVo();
            empVo.setShopId(shop.getShopId());
            empVo.setShopName(shop.getShopName());
            empVo.setShopAddress(shop.getShopAddress());
            empVo.setManagers(kpAdminService.searchAdminNameByShopIdAndEmployee(shop.getShopId(), "店长"));
            empVo.setNewClientSize(clientService.selectNewClientCountByShopAndRegisterDate(shop.getShopId(), startDate, endDate));
            vo.add(empVo);
        }
        for (RentOutLog log : logList) {
            for (ShopStatisticsVo itemVo : vo) {
                if (log.getLogShop().equals(itemVo.getShopName())) {
                    itemVo.setRentOutSize(itemVo.getRentOutSize() + 1);
                    itemVo.setRentOutTotalMoney(itemVo.getRentOutTotalMoney() + (log.getActualCollectionMoney() < 0 ? 0 : log.getActualCollectionMoney()));
                }
            }
        }
        return vo;
    }

    @Override
    public Map<String, List<String>> searchShopEmployee(Integer shopId) {
        List<KpAdmin> list = kpAdminService.list(new LambdaQueryWrapper<KpAdmin>().eq(shopId != null && shopId != 0, KpAdmin::getAdminShop, shopId));
        Map<String, List<String>> map = new HashMap<>();
        for (KpAdmin admin : list) {
            List<String> roleNames = kpRoleService.searchRoleNameByKpAdminId(admin.getAdminId());
            for (String name : roleNames) {
                if (!map.containsKey(name)) {
                    map.put(name, new ArrayList<>());
                }
                map.get(name).add("<span style='display: inline-block;width: 130px'>姓名：" + admin.getAdminName() + "</span> 手机号：" + admin.getAdminPhone());
            }
        }
        return map;
    }
}