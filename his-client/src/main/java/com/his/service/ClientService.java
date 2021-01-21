package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.Client;

import java.time.LocalDate;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-21
 *
 * @author yh
 */
public interface ClientService extends IService<Client> {
    Page<Client> searchPage(Integer current, Integer size,String clientIdNumber, String clientName, String clientPhone, String clientAddress, Boolean clientSex);


    List<Client> getSizeListByClientIdNumber(String clientIdNumber, Integer size);

    List<String> getClientIdByClientName(String clientName);

    List<String> getClientIdNumberByClientName(String clientName);

    Integer selectNewClientCountByShopAndRegisterDate(Integer shopId, LocalDate startDate, LocalDate endDate);
}
