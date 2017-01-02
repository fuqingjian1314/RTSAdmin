package com.sjg.sys.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private Long id;

    /**
     * 0：启用
     * 1：禁用
     */
    private Byte locked;

    private String loginName;

    private String loginPwd;

    private String name;

    private String phone;

    private String pinyin;

    private Long orgId;

    private Date createTime;

    private Long createrId;

    private String createrName;

    private Date updateTime;

    private Long updaterId;

    private String updaterName;

    private String gender;

    private String sex;

    private String createrOrganization;

    private String updaterOrganization;

    private Long positionLevel;

    private String avator;

    private String description;

    private String seatNumber;

    private String attNumber;

    private Long vipNumber;

    private Long isPattern;

    private Long customerNumber;

    private Long workArea;

    private String expressPwd;

    private Integer integral;

    private String integralLevel;
    
    private BigDecimal restscore;
    
    /***********************************格式化数据*********************************************/
    private String createDateFormat;
    private String lockFormat;
    
    public String getLockFormat(){
    	if(this.locked != null ){
    		return this.locked == 0 ? "启用":"禁用";
    	}else{
    		return "";
    	}
    }
    
    public String getCreateDateFormat(){
    	if(this.createTime!= null){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(this.createTime);
    	}else{
    		return "";
    	}
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getLocked() {
        return this.locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return this.loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreaterId() {
        return this.createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return this.createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return this.updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return this.updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName == null ? null : updaterName.trim();
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCreaterOrganization() {
        return this.createrOrganization;
    }

    public void setCreaterOrganization(String createrOrganization) {
        this.createrOrganization = createrOrganization == null ? null : createrOrganization.trim();
    }

    public String getUpdaterOrganization() {
        return this.updaterOrganization;
    }

    public void setUpdaterOrganization(String updaterOrganization) {
        this.updaterOrganization = updaterOrganization == null ? null : updaterOrganization.trim();
    }

    public Long getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(Long positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator == null ? null : avator.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber == null ? null : seatNumber.trim();
    }

    public String getAttNumber() {
        return attNumber;
    }

    public void setAttNumber(String attNumber) {
        this.attNumber = attNumber == null ? null : attNumber.trim();
    }

    public Long getVipNumber() {
        return vipNumber;
    }

    public void setVipNumber(Long vipNumber) {
        this.vipNumber = vipNumber;
    }

    public Long getIsPattern() {
        return isPattern;
    }

    public void setIsPattern(Long isPattern) {
        this.isPattern = isPattern;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Long getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Long workArea) {
        this.workArea = workArea;
    }

    public String getExpressPwd() {
        return expressPwd;
    }

    public void setExpressPwd(String expressPwd) {
        this.expressPwd = expressPwd == null ? null : expressPwd.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getIntegralLevel() {
        return integralLevel;
    }

    public void setIntegralLevel(String integralLevel) {
        this.integralLevel = integralLevel == null ? null : integralLevel.trim();
    }

	public BigDecimal getRestscore() {
		return restscore;
	}

	public void setRestscore(BigDecimal restscore) {
		this.restscore = restscore;
	}
}