package com.sjg.sys.entity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 异常条件查询
 * @author Hel
 *
 */
public class LogQueryParm {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date occurStartTime;
	/**
	 * 注册日期开始结束
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date occurEndTime;

	/**
	 * 当前用户
	 */
	private String lrLoginName;

	/**
	 * 访问者
	 */
	private String createby;

	/**
	 * 日志类型(0:操作日志；1：异常日志)
	 */
	private String logType;

	public Date getOccurStartTime() {
		return occurStartTime;
	}

	public void setOccurStartTime(Date occurStartTime) {
		this.occurStartTime = occurStartTime;
	}

	public Date getOccurEndTime() {
		return occurEndTime;
	}

	public void setOccurEndTime(Date occurEndTime) {
		this.occurEndTime = occurEndTime;
	}

	public String getLrLoginName() {
		return lrLoginName;
	}

	public void setLrLoginName(String lrLoginName) {
		this.lrLoginName = lrLoginName;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

}
