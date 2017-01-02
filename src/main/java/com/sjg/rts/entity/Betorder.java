package com.sjg.rts.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Betorder {

	/**
	 * 主键ID
	 */
	private Long orderId;
	/**
	 * 期号
	 */
	private Long fullPeriodNumber;
	/**
	 * 球号
	 */
	private Integer ballnumber;
	/**
	 * 类型
	 */
	private String bettype;
	/**
	 * 金额
	 */
	private BigDecimal cost;
	/**
	 * 赔率
	 */
	private BigDecimal rate;
	/**
	 * 输赢结果
	 */
	private BigDecimal bunkoresult;
	/**
	 * 时间
	 */
	private Timestamp bettime;
	/**
	 * 星期
	 */
	private String betweek;
	/**
	 * 创建用户ID
	 */
	private String cjyhid;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getFullPeriodNumber() {
		return fullPeriodNumber;
	}

	public void setFullPeriodNumber(Long fullPeriodNumber) {
		this.fullPeriodNumber = fullPeriodNumber;
	}

	public Integer getBallnumber() {
		return ballnumber;
	}

	public void setBallnumber(Integer ballnumber) {
		this.ballnumber = ballnumber;
	}

	public String getBettype() {
		return bettype;
	}

	public void setBettype(String bettype) {
		this.bettype = bettype;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getBunkoresult() {
		return bunkoresult;
	}

	public void setBunkoresult(BigDecimal bunkoresult) {
		this.bunkoresult = bunkoresult;
	}

	public Timestamp getBettime() {
		return bettime;
	}

	public void setBettime(Timestamp bettime) {
		this.bettime = bettime;
	}

	public String getBetweek() {
		return betweek;
	}

	public void setBetweek(String betweek) {
		this.betweek = betweek;
	}

	public String getCjyhid() {
		return cjyhid;
	}

	public void setCjyhid(String cjyhid) {
		this.cjyhid = cjyhid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
