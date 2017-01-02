package com.sjg.sys.entity;

import java.io.Serializable;

public class Resources implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String description;

    private String icon;
	/**
	 * 0隐藏  1未隐藏
	 */
    private Integer isHide;

    private String resKey;

    private String name;

    private Long pid;

    private Integer sort;
    /**
	 * type 资源类型 1菜单、2页面、3数据、4按钮
	 */
    private Integer type;

    private String url;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey == null ? null : resKey.trim();
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    
    @Override
    public boolean equals(Object obj){
    	if( this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(this.getClass()!=obj.getClass()){
			return false;
		}
		final Resources  otherRes=(Resources) obj;
		if(this.getId() != otherRes.getId()){
			return false;
		}
		return true;
    }
    
}