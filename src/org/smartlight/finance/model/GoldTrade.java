package org.smartlight.finance.model;

import java.util.Date;

public class GoldTrade {
    private Integer id;

    private Double tradePrice;
    
    private Integer tradeMethod;
    
    private Integer tradeMode;

    private Integer tradeAmount;

    private Date tradeDate;

    private String tradeRemark;

    private Double stopLossPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Integer getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Integer tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeRemark() {
        return tradeRemark;
    }

    public void setTradeRemark(String tradeRemark) {
        this.tradeRemark = tradeRemark == null ? null : tradeRemark.trim();
    }

    public Double getStopLossPrice() {
        return stopLossPrice;
    }

    public void setStopLossPrice(Double stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

	public Integer getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(Integer tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public Integer getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(Integer tradeMode) {
		this.tradeMode = tradeMode;
	}
}