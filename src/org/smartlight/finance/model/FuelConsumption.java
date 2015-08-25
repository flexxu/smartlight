package org.smartlight.finance.model;

import java.util.Date;

public class FuelConsumption {
    private Integer id;

    private Double fuelAmount;

    private Double fuelPrice;

    private Double totalCost;

    private Date opDate;

    private String speedometerReading;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public Double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(Double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public String getSpeedometerReading() {
        return speedometerReading;
    }

    public void setSpeedometerReading(String speedometerReading) {
        this.speedometerReading = speedometerReading == null ? null : speedometerReading.trim();
    }
}