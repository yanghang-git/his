package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.RentOutLog;
import org.apache.ibatis.annotations.Select;

public interface RentOutLogMapper extends BaseMapper<RentOutLog> {
    @Select("SELECT `log_numbers` FROM `rent_out_log` WHERE `log_numbers` LIKE CONCAT((SELECT DATE_FORMAT(NOW(), '%Y%m%d')), '%') ORDER BY SUBSTRING(`log_numbers`, 9)+0 DESC LIMIT 1")
    String getLastOddNumbers();
}
