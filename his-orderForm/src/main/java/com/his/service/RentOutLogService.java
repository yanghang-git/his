package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.RentOutLog;

public interface RentOutLogService extends IService<RentOutLog> {

    Page<RentOutLog> searchPage(Integer page, Integer limit, String clientName, String logNumbers, String ofTheTime, String actualReturnTime);

    String getLastOddNumbers();

}
