package com.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.RentOut;

import java.util.List;


public interface RentOutService1 extends IService<RentOut> {
    boolean checkClientWhetherBeingLease(List<String> clientIdList);

}
