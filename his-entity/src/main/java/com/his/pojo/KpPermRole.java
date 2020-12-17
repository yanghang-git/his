package com.his.pojo;


import java.io.Serializable;

public class KpPermRole implements Serializable {

  private static final long serialVersionUID = -3980860007560351062L;
  private Integer permId;
  private Integer roleId;

  KpPermRole() {}

  public KpPermRole(Integer permId, Integer roleId) {
    this.permId = permId;
    this.roleId = roleId;
  }

  public Integer getPermId() {
    return permId;
  }

  public void setPermId(Integer permId) {
    this.permId = permId;
  }


  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Override
  public String toString() {
    return "KpPermRole{" +
            "permId=" + permId +
            ", roleId=" + roleId +
            '}';
  }
}
