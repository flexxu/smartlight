package org.smartlight.finance.model;

public class StockWatch {
    private Integer id;

    private String stockName;

    private String stockCode;

    private Float watchtop;

    private Float watchbottom;

    private Byte watchinterval;

    private Boolean validFlag;

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

    public Float getWatchtop() {
        return watchtop;
    }

    public void setWatchtop(Float watchtop) {
        this.watchtop = watchtop;
    }

    public Float getWatchbottom() {
        return watchbottom;
    }

    public void setWatchbottom(Float watchbottom) {
        this.watchbottom = watchbottom;
    }

    public Byte getWatchinterval() {
        return watchinterval;
    }

    public void setWatchinterval(Byte watchinterval) {
        this.watchinterval = watchinterval;
    }

    public Boolean getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Boolean validFlag) {
        this.validFlag = validFlag;
    }
}