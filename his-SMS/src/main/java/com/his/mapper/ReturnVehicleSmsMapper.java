package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.ReturnVehicleSms;
import org.apache.ibatis.annotations.Select;

/**
 * Description: TO DO
 * Date: 21-1-4
 *
 * @author yh
 */
public interface ReturnVehicleSmsMapper extends BaseMapper<ReturnVehicleSms> {

    @Select("select * from return_vehicle_sms order by sms_time limit 1")
    ReturnVehicleSms selectRecent();
}
