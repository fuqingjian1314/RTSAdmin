package com.sjg.sys.utils;
/**
 * 操作按钮
 * @author fqj
 *
 */
public class Button {
	/**
	 * 按钮名称
	 */
	private String name;
	/**
	 * 按钮方法
	 */
	private String jsMethod;
	/**
	 * 按钮class类
	 */
	private String className;

	public Button(String name, String jsMethod) {
		super();
		this.name = name;
		this.jsMethod = jsMethod;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJsMethod() {
		return jsMethod;
	}
	public void setJsMethod(String jsMethod) {
		this.jsMethod = jsMethod;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	

}
