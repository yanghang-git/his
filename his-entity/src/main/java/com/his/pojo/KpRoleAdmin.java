package com.his.pojo;

import java.io.Serializable;

public class KpRoleAdmin implements Serializable {

  private static final long serialVersionUID = -2854909575864471801L;
  private Integer roleId;
  private Integer adminId;

  public KpRoleAdmin() {}

  public KpRoleAdmin(Integer roleId, Integer adminId) {
    this.roleId = roleId;
    this.adminId = adminId;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }

  @Override
  public String toString() {
    return "KpRoleAdmin{" +
            "roleId='" + roleId + '\'' +
            ", adminId=" + adminId +
            '}';
  }
}
