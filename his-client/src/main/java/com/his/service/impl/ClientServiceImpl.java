package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.ClientMapper;
import com.his.pojo.Client;
import com.his.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-21
 *
 * @author yh
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Override
    public Page<Client> searchPage(Integer current, Integer size, String clientIdNumber, String clientName, String clientPhone, String clientAddress, Boolean clientSex) {
        Page<Client> page = new Page<>(current,size);
        LambdaQueryWrapper<Client> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(clientSex != null, Client::getClientSex, clientSex);
        wrapper.like(!StringUtils.isEmpty(clientIdNumber), Client::getClientIdNumber, clientIdNumber);
        wrapper.like(!StringUtils.isEmpty(clientName), Client::getClientName, clientName);
        wrapper.like(!StringUtils.isEmpty(clientPhone), Client::getClientPhone, clientPhone);
        wrapper.like(!StringUtils.isEmpty(clientAddress), Client::getClientAddress, clientAddress);
        return page(page, wrapper);
    }


    @Override
    public List<Client> getSizeListByClientIdNumber(String clientIdNumber, Integer size) {
        LambdaQueryWrapper<Client> wrapper = new LambdaQueryWrapper<>();
        Page<Client> page = new Page<>(1, size);
        wrapper.likeRight(!StringUtils.isEmpty(clientIdNumber), Client::getClientIdNumber, clientIdNumber);
        return page(page, wrapper).getRecords();
    }

    @Override
    public List<String> getClientIdByClientName(String clientName) {
        return null;
    }

    @Override
    public List<String> getClientIdNumberByClientName(String clientName) {
        if (StringUtils.isEmpty(clientName)) {
            return null;
        } else {
            LambdaQueryWrapper<Client> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(Client::getClientName, clientName);
            return list(wrapper).stream().map(Client::getClientIdNumber).collect(Collectors.toList());
        }
    }

    @Override
    public Integer selectNewClientCountByShopAndRegisterDate(Integer shopId, LocalDate startDate, LocalDate endDate) {
        LambdaQueryWrapper<Client> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(shopId != null && shopId != 0, Client::getRegisterShop, shopId)
            .ge(startDate != null, Client::getCreateTime, startDate)
            .lt(endDate != null, Client::getCreateTime, endDate);
        return count(wrapper);
    }
}