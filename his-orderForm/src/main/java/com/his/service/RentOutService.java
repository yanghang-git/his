package com.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.RentOut;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
public interface RentOutService extends IService<RentOut> {

    String getLastOddNumbers();
}
