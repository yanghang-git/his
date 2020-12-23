package com.his.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Client implements Serializable {

  private static final long serialVersionUID = -5623820470740449396L;
  @TableId(type = IdType.INPUT, value = "client_ID_number")
  private String clientIdNumber;
  private String clientName;
  private String clientPhone;
  private Boolean clientSex;
  private String clientAddress;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // 配合@RequestBody使用. 请求方式为application/json时使用
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") // 请求方式为application/x-www-form-urlencoded时使用 form请求方式
  private LocalDateTime createTime;

  public Client() {}

  public Client(String clientIdNumber, String clientName, String clientPhone, Boolean clientSex, String clientAddress, LocalDateTime createTime) {
    this.clientIdNumber = clientIdNumber;
    this.clientName = clientName;
    this.clientPhone = clientPhone;
    this.clientSex = clientSex;
    this.clientAddress = clientAddress;
    this.createTime = createTime;
  }

  public String getClientIdNumber() {
    return clientIdNumber;
  }

  public void setClientIdNumber(String clientIdNumber) {
    this.clientIdNumber = clientIdNumber;
  }


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }


  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }


  public Boolean getClientSex() {
    return clientSex;
  }

  public void setClientSex(Boolean clientSex) {
    this.clientSex = clientSex;
  }


  public String getClientAddress() {
    return clientAddress;
  }

  public void setClientAddress(String clientAddress) {
    this.clientAddress = clientAddress;
  }


  public java.time.LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.time.LocalDateTime createTime) {
    this.createTime = createTime;
  }


  @Override
  public String toString() {
    return "Client{" +
            "clientIdNumber='" + clientIdNumber + '\'' +
            ", clientName='" + clientName + '\'' +
            ", clientPhone='" + clientPhone + '\'' +
            ", clientSex='" + clientSex + '\'' +
            ", clientAddress='" + clientAddress + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}
