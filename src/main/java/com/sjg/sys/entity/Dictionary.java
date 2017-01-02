package com.sjg.sys.entity;

import java.util.List;


public class Dictionary {

    private Long id;

    private String code;

    private String description;

    private Long pid;

    private String value;

    private Long sort;

    private Long isHide;//是否隐藏 0：隐藏，1：未隐藏

    private Long type;//类型 0 系统参数  1业务参数

    private String imgSrc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getIsHide() {
        return isHide;
    }

    public void setIsHide(Long isHide) {
        this.isHide = isHide;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

}