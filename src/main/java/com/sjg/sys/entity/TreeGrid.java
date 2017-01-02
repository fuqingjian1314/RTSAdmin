package com.sjg.sys.entity;

public class TreeGrid {
	private Long id;
	private Long pId;
	private String name;
	private String code;
	private String nickname;
	private int state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public TreeGrid() {
	}
	public TreeGrid(Long id, Long pId, String name, String code, String nickname, int state) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.code = code;
		this.nickname = nickname;
		this.state = state;
	}
}
