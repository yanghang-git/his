package com.his.pojo;




public class RentOutVehicle {

	/**
	 * 出租单号
	 */
	private String oddNumbers;

	/**
	 * 车辆表id（外键）
	 */
	private String vehiclePlateNumber;

	/**
	 * 租借价格
	 */
	private Double rentOutMoney;

	/**
	 * 押金
	 */
	private Double rentalDeposit;

  public RentOutVehicle() {}

  public RentOutVehicle(String oddNumbers, String vehiclePlateNumber, Double rentOutMoney, Double rentalDeposit) {
    this.oddNumbers = oddNumbers;
    this.vehiclePlateNumber = vehiclePlateNumber;
    this.rentOutMoney = rentOutMoney;
    this.rentalDeposit = rentalDeposit;
  }

  public String getOddNumbers() {
    return oddNumbers;
  }

  public void setOddNumbers(String oddNumbers) {
    this.oddNumbers = oddNumbers;
  }

  public String getVehiclePlateNumber() {
    return vehiclePlateNumber;
  }

  public void setVehiclePlateNumber(String vehiclePlateNumber) {
    this.vehiclePlateNumber = vehiclePlateNumber;
  }

  public Double getRentOutMoney() {
    return rentOutMoney;
  }

  public void setRentOutMoney(Double rentOutMoney) {
    this.rentOutMoney = rentOutMoney;
  }

  public Double getRentalDeposit() {
    return rentalDeposit;
  }

  public void setRentalDeposit(Double rentalDeposit) {
    this.rentalDeposit = rentalDeposit;
  }


  @Override
  public String toString() {
    return "RentOutVehicle{" +
            "oddNumbers='" + oddNumbers + '\'' +
            ", vehiclePlateNumber='" + vehiclePlateNumber + '\'' +
            ", rentOutMoney=" + rentOutMoney +
            ", rentalDeposit=" + rentalDeposit +
            '}';
  }
}
