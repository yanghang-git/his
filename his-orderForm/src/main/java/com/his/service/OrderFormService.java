package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.RentOut;
import com.his.vo.RentOutVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
public interface OrderFormService {

    Boolean addRentOutOrderForm(RentOutVo vo);

    Page<RentOut> select(Integer page, Integer limit, String clientName, String oddNumbers , String ofTheTimeStart, String ofTheTimeEnd);


    boolean cancelOrderForm(RentOut rentOut);
}