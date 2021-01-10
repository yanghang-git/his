package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.RentOutLog;

public interface OrderHistoryService {

    Page<RentOutLog> select(Integer page, Integer limit,String clientName, String logNumbers,String ofTheTime, String actualReturnTime);

}
