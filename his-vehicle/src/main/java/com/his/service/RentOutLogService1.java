package com.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.RentOutLog;
import com.his.vo.RentOutLogVo;

import java.util.List;

public interface RentOutLogService1 extends IService<RentOutLog> {

    List<RentOutLog> addRentOutLogList(RentOutLogVo vo);

}
