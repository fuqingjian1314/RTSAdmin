package com.sjg.sys.entity.vo;

import java.util.List;

public class DictionaryVo {

	private Long id;

    private String code;

    private String description;

    private Long pid;

    private String value;

    private Long sort;
    
    private Long  type;//类型 0 系统参数  1业务参数

    private Long isHide;//是否隐藏 0：隐藏，1：未隐藏

    private String imgSrc;
    
    List<DictionaryVo> childrenList;
    
    private Long typeParentId;
    
    public Long getTypeParentId() {
    	return typeParentId;
    }
    
    public void setTypeParentId(Long typeParentId) {
    	this.typeParentId = typeParentId;
    }

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

    public String getImgSrc() {
		return imgSrc;
	}

    public void setImgSrc(String imgSrc) {
	this.imgSrc = imgSrc;
    }
	public List<DictionaryVo> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<DictionaryVo> childrenList) {
		this.childrenList = childrenList;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
    
}