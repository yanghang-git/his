package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutMapper;
import com.his.pojo.KpAdmin;
import com.his.pojo.RentOut;
import com.his.service.RentOutService;
import org.apache.shiro.SecurityUtils;
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
     * @param generateTimeStart 租车区间的最小时间
     * @param generateTimeEnd   租车区间的最大时间
     * @return
     */
    @Override
    public Page<RentOut> searchPageByKeyword(Integer current, Integer size, String oddNumbers, List<String> clientIdNumberList, String  generateTimeStart, String generateTimeEnd) {
        KpAdmin admin = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        Page<RentOut> page = new Page<>(current, size);
        LambdaQueryWrapper<RentOut> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOut::getRentOutShop, admin.getAdminShop());
        wrapper.eq(!StringUtils.isEmpty(oddNumbers),RentOut::getOddNumbers, oddNumbers);
        wrapper.in(clientIdNumberList != null && clientIdNumberList.size() > 0, RentOut::getClientId, clientIdNumberList);
        wrapper.ge(!StringUtils.isEmpty(generateTimeStart) ,RentOut::getGenerateTime ,generateTimeStart);
        wrapper.le(!StringUtils.isEmpty(generateTimeEnd) , RentOut::getGenerateTime , generateTimeEnd);
        return page(page, wrapper);
    }   
}
