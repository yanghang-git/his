package com.his.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.his.eunms.CallVehicleState;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CallVehicleRecord implements Serializable {

  private static final long serialVersionUID = -4738839669946090568L;
  @TableId(type = IdType.AUTO)
  private Integer recordId;
  private String licensePlateNumber;
  private Integer borrowCarShop;
  private LocalDateTime borrowCarTime;
  private Integer beBorrowCarShop;
  private LocalDateTime beBorrowCarTime;
  private CallVehicleState state;
  private Integer applyOperator;
  private Integer applyApproveOperator;
  private Integer returnOperator;
  private Integer returnReceiveOperator;

  public CallVehicleRecord() {}

  public CallVehicleRecord(Integer recordId, String licensePlateNumber, Integer borrowCarShop, LocalDateTime borrowCarTime, Integer beBorrowCarShop, LocalDateTime beBorrowCarTime, CallVehicleState state, Integer applyOperator, Integer applyApproveOperator, Integer returnOperator, Integer returnReceiveOperator) {
    this.recordId = recordId;
    this.licensePlateNumber = licensePlateNumber;
    this.borrowCarShop = borrowCarShop;
    this.borrowCarTime = borrowCarTime;
    this.beBorrowCarShop = beBorrowCarShop;
    this.beBorrowCarTime = beBorrowCarTime;
    this.state = state;
    this.applyOperator = applyOperator;
    this.applyApproveOperator = applyApproveOperator;
    this.returnOperator = returnOperator;
    this.returnReceiveOperator = returnReceiveOperator;
  }

  public Integer getRecordId() {
    return recordId;
  }

  public void setRecordId(Integer recordId) {
    this.recordId = recordId;
  }


  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

  public void setLicensePlateNumber(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }


  public Integer getBorrowCarShop() {
    return borrowCarShop;
  }

  public void setBorrowCarShop(Integer borrowCarShop) {
    this.borrowCarShop = borrowCarShop;
  }


  public LocalDateTime getBorrowCarTime() {
    return borrowCarTime;
  }

  public void setBorrowCarTime(LocalDateTime borrowCarTime) {
    this.borrowCarTime = borrowCarTime;
  }


  public Integer getBeBorrowCarShop() {
    return beBorrowCarShop;
  }

  public void setBeBorrowCarShop(Integer beBorrowCarShop) {
    this.beBorrowCarShop = beBorrowCarShop;
  }


  public LocalDateTime getBeBorrowCarTime() {
    return beBorrowCarTime;
  }

  public void setBeBorrowCarTime(LocalDateTime beBorrowCarTime) {
    this.beBorrowCarTime = beBorrowCarTime;
  }


  public CallVehicleState getState() {
    return state;
  }

  public void setState(CallVehicleState state) {
    this.state = state;
  }


  public Integer getApplyOperator() {
    return applyOperator;
  }

  public void setApplyOperator(Integer applyOperator) {
    this.applyOperator = applyOperator;
  }


  public Integer getApplyApproveOperator() {
    return applyApproveOperator;
  }

  public void setApplyApproveOperator(Integer applyApproveOperator) {
    this.applyApproveOperator = applyApproveOperator;
  }


  public Integer getReturnOperator() {
    return returnOperator;
  }

  public void setReturnOperator(Integer returnOperator) {
    this.returnOperator = returnOperator;
  }


  public Integer getReturnReceiveOperator() {
    return returnReceiveOperator;
  }

  public void setReturnReceiveOperator(Integer returnReceiveOperator) {
    this.returnReceiveOperator = returnReceiveOperator;
  }


  @Override
  public String toString() {
    return "CallVehicleRecord{" +
            "recordId=" + recordId +
            ", licensePlateNumber='" + licensePlateNumber + '\'' +
            ", borrowCarShop=" + borrowCarShop +
            ", borrowCarTime=" + borrowCarTime +
            ", beBorrowCarShop=" + beBorrowCarShop +
            ", beBorrowCarTime=" + beBorrowCarTime +
            ", state=" + state +
            ", applyOperator=" + applyOperator +
            ", applyApproveOperator=" + applyApproveOperator +
            ", returnOperator=" + returnOperator +
            ", returnReceiveOperator=" + returnReceiveOperator +
            '}';
  }
}
