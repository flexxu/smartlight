package org.smartlight.metatrader.model;

import java.util.Date;

public class TradeBid {
    private Integer id;

    private Integer opType;

    private Double opPrice;

    private Date opDate;

    private Integer opAmount;

    private String remark;

    private String opCode;

    private Integer opFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Double getOpPrice() {
        return opPrice;
    }

    public void setOpPrice(Double opPrice) {
        this.opPrice = opPrice;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public Integer getOpAmount() {
        return opAmount;
    }

    public void setOpAmount(Integer opAmount) {
        this.opAmount = opAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode == null ? null : opCode.trim();
    }

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}
}