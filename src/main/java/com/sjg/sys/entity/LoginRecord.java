package com.sjg.sys.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录日志
 * @author Hel
 *
 */
public class LoginRecord {
	private Long lrId;

	private String lrLoginName;

	private Date lrLoginTime;

	private String lrLoginIp;

	private String lrLoginStatus;

	private String formatLoginTime;
    
    public String getFormatLoginTime(){
    	if(this.lrLoginTime == null ){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(this.lrLoginTime);
    }
	
	public Long getLrId() {
		return lrId;
	}

	public void setLrId(Long lrId) {
		this.lrId = lrId;
	}

	public String getLrLoginName() {
		return lrLoginName;
	}

	public void setLrLoginName(String lrLoginName) {
		this.lrLoginName = lrLoginName == null ? null : lrLoginName.trim();
	}

	public Date getLrLoginTime() {
		return lrLoginTime;
	}

	public void setLrLoginTime(Date lrLoginTime) {
		this.lrLoginTime = lrLoginTime;
	}

	public String getLrLoginIp() {
		return lrLoginIp;
	}

	public void setLrLoginIp(String lrLoginIp) {
		this.lrLoginIp = lrLoginIp == null ? null : lrLoginIp.trim();
	}

	public String getlrLoginStatus() {
		return lrLoginStatus;
	}

	public void setlrLoginStatus(String lrLoginStatus) {
		this.lrLoginStatus = lrLoginStatus == null ? null : lrLoginStatus.trim();
	}
}