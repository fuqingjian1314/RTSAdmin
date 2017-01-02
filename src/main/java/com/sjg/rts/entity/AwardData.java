package com.sjg.rts.entity;

import java.sql.Timestamp;

/**
 * 开奖数据
 */
public class AwardData {
	private Timestamp time;
	private Integer firstPeriod;
	private Integer apiVersion;
	private Award current;
	private Award next;
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Award getCurrent() {
		return current;
	}

	public void setCurrent(Award current) {
		this.current = current;
	}

	public Award getNext() {
		return next;
	}

	public void setNext(Award next) {
		this.next = next;
	}

	public Integer getFirstPeriod() {
		return firstPeriod;
	}

	public void setFirstPeriod(Integer firstPeriod) {
		this.firstPeriod = firstPeriod;
	}

	public Integer getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(Integer apiVersion) {
		this.apiVersion = apiVersion;
	}

	@Override
	public String toString() {
		return "AwardData{" +
				"time=" + time +
				", firstPeriod=" + firstPeriod +
				", apiVersion=" + apiVersion +
				", current=" + current.toString() +
				", next=" + next.toString() +
				'}';
	}
}
