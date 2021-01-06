package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.RentOut;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
public interface RentOutService extends IService<RentOut> {

    String getLastOddNumbers();
    /**
     *  根据条件查询内容
     * @param oddNumbers 订单编号
     * @param clientIdNumberList 客户身份证(主键)集合
     * @param generateTimeStart 租车区间的最小时间
     * @param generateTimeEnd 租车区间的最大时间
     * @return
     */
    Page<RentOut> searchPageByKeyword(Integer current, Integer size, String oddNumbers, List<String> clientIdNumberList, String generateTimeStart, String generateTimeEnd);

}
