package com.his.mapper;

import com.his.pojo.Client;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-24
 *
 * @author yh
 */
public interface StatisticsClientMapper {

    @Select("select * from client")
    List<Client> clientList();
}
