package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutMapper;
import com.his.pojo.RentOut;
import com.his.service.RentOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
@Service
public class RentOutServiceImpl extends ServiceImpl<RentOutMapper, RentOut> implements RentOutService {

    @Autowired
    private RentOutMapper rentOutMapper;


    @Override
    public String getLastOddNumbers() {
        return rentOutMapper.getLastOddNumbers();
    }

    /**
     * 根据条件查询内容
     *
     * @param oddNumbers     订单编号
     * @param clientIdNumberList     客户id
     * @param ofTheTimeStart 租车区间的最小时间
     * @param ofTheTimeEnd   租车区间的最大时间
     * @return
     */
    @Override
    public Page<RentOut> searchPageByKeyword(Integer current, Integer size, String oddNumbers, List<String> clientIdNumberList, String  ofTheTimeStart, String ofTheTimeEnd) {

        Page<RentOut> page = new Page<>(current, size);
        LambdaQueryWrapper<RentOut> wrapper = new LambdaQueryWrapper<>();
        // 字符串        取反         是不是空的
        wrapper.eq(!StringUtils.isEmpty(oddNumbers),RentOut::getOddNumbers, oddNumbers);
        //
        wrapper.in(clientIdNumberList != null && clientIdNumberList.size() > 0, RentOut::getClientId, clientIdNumberList);

        wrapper.ge(!StringUtils.isEmpty(ofTheTimeStart) ,RentOut::getGenerateTime ,ofTheTimeStart);
        wrapper.le(!StringUtils.isEmpty(ofTheTimeEnd) , RentOut::getGenerateTime , ofTheTimeEnd);
        return page(page, wrapper);
    }
}
