package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.KpPermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-11
 *
 * @author yh
 */
public interface KpPermissionMapper extends BaseMapper<KpPermission> {

    @Select("<script>" +
            "select perm_code from kp_permission where perm_code is not null and perm_id in (" +
                "select perm_id from kp_perm_role where role_id in " +
                    "<foreach collection='array' item='roleId' separator=',' open='(' close=')'> #{roleId}</foreach>" +
            ")" +
            "</script>")
    List<String> searchPermCodeList(Integer[] roleId);
}
