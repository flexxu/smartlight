package org.smartlight.finance.model;

import java.util.Date;

public class Stock {
    private Integer id;

    private String stockName;

    private String stockCode;

    private Float stockClose;

    private Float stockOpen;

    private Float stockHigh;

    private Float stockLow;

    private Integer stockVol;

    private Date opTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public Float getStockClose() {
        return stockClose;
    }

    public void setStockClose(Float stockClose) {
        this.stockClose = stockClose;
    }

    public Float getStockOpen() {
        return stockOpen;
    }

    public void setStockOpen(Float stockOpen) {
        this.stockOpen = stockOpen;
    }

    public Float getStockHigh() {
        return stockHigh;
    }

    public void setStockHigh(Float stockHigh) {
        this.stockHigh = stockHigh;
    }

    public Float getStockLow() {
        return stockLow;
    }

    public void setStockLow(Float stockLow) {
        this.stockLow = stockLow;
    }

    public Integer getStockVol() {
        return stockVol;
    }

    public void setStockVol(Integer stockVol) {
        this.stockVol = stockVol;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}