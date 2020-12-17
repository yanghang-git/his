package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.KpPermRole;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-15
 *
 * @author yh
 */
public interface KpPermRoleMapper extends BaseMapper<KpPermRole> {

    @Insert("<script>" +
            "insert into kp_perm_role(perm_id, role_id) values " +
            "<foreach collection='list' item='permRole' separator=','> (#{permRole.permId}, #{permRole.roleId})</foreach>" +
            "</script>")
    Boolean insertBatch(List<KpPermRole> entityList);

}
