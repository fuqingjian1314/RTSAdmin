package com.sjg.rts.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据库表映射对象
 * @author Administrator
 * 修改历史
 */
public class ChargeRecordVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID信息
	 */
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户充值分
	 */
	private BigDecimal chargeScore;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者id
	 */
	private Long createrId;
	/**
	 * 创建人名字
	 */
	private String createrName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getChargeScore() {
		return chargeScore;
	}

	public void setChargeScore(BigDecimal chargeScore) {
		this.chargeScore = chargeScore;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

}
