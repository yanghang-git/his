package com.his.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class KpAdmin implements Serializable {

  private static final long serialVersionUID = -7682820027424673857L;
  private Integer userId;
  private String loginName;
  private String username;
  private String email;
  private Boolean gender;
  private String mobileNo;
  private String birthDate;
  private String password;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createTime;
  private String address;
  private String isDel;

  public KpAdmin() {}

  public KpAdmin(Integer userId, String loginName, String username, String email, Boolean gender, String mobileNo, String birthDate, String password, LocalDateTime createTime, String address, String isDel) {
    this.userId = userId;
    this.loginName = loginName;
    this.username = username;
    this.email = email;
    this.gender = gender;
    this.mobileNo = mobileNo;
    this.birthDate = birthDate;
    this.password = password;
    this.createTime = createTime;
    this.address = address;
    this.isDel = isDel;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }


  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }


  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public java.time.LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.time.LocalDateTime createTime) {
    this.createTime = createTime;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getIsDel() {
    return isDel;
  }

  public void setIsDel(String isDel) {
    this.isDel = isDel;
  }

  @Override
  public String toString() {
    return "KpUser{" +
            "userId=" + userId +
            ", loginName='" + loginName + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", gender=" + gender +
            ", mobileNo='" + mobileNo + '\'' +
            ", birthDate='" + birthDate + '\'' +
            ", password='" + password + '\'' +
            ", createTime=" + createTime +
            ", address='" + address + '\'' +
            ", isDel='" + isDel + '\'' +
            '}';
  }
}
