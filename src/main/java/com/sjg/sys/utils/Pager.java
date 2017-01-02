package com.sjg.sys.utils;

import java.util.List;

public class Pager<T> {
	/**
	 * 总条数
	 */
	private Long rowsCount;
	/**
	 * 对象列表
	 */
	private List<T> items;
	public Long getRowsCount() {
		return rowsCount;
	}
	public void setRowsCount(Long rowsCount) {
		this.rowsCount = rowsCount;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}

}
