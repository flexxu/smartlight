package org.smartlight.finance.model;

import java.util.Date;

public class GoldPrice {
    private Integer id;

    private String name;

    private String desc;

    private Double open;

    private Double close;

    private Double high;

    private Double low;

    private Date opTime;

    private String tradeDept;

    private Integer volReal;

    private Integer volTick;

    private Double spread;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getTradeDept() {
        return tradeDept;
    }

    public void setTradeDept(String tradeDept) {
        this.tradeDept = tradeDept == null ? null : tradeDept.trim();
    }

    public Integer getVolReal() {
        return volReal;
    }

    public void setVolReal(Integer volReal) {
        this.volReal = volReal;
    }

    public Integer getVolTick() {
        return volTick;
    }

    public void setVolTick(Integer volTick) {
        this.volTick = volTick;
    }

    public Double getSpread() {
        return spread;
    }

    public void setSpread(Double spread) {
        this.spread = spread;
    }
}