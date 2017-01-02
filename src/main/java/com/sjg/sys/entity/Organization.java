package com.sjg.sys.entity;

import java.util.Date;

public class Organization {
	
	/**
	 * 主键
	 */
    private Long id;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否隐藏   0：隐藏    1：显示
     */
    private Byte isHide;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 排序 
     */
    private Integer sort;

    
    private Date createTime;

    private Long createrId;

    private String createrName;

    private Date updateTime;

    private Long updaterId;

    private String updaterName;

    private Long resuser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getIsHide() {
        return isHide;
    }

    public void setIsHide(Byte isHide) {
        this.isHide = isHide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        this.createrName = createrName == null ? null : createrName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName == null ? null : updaterName.trim();
    }

    public Long getResuser() {
        return resuser;
    }

    public void setResuser(Long resuser) {
        this.resuser = resuser;
    }
}