package com.sjg.sys.entity.vo;

import java.util.List;

import com.sjg.sys.entity.Resources;

/**
 * 菜单VO对象
 * @author huangliang
 *
 */
public class ResourceMenuVo {
	/**
	 * 父菜单
	 */
	private Resources parentRes;
	
	/**
	 * 是否有子菜单  ture 表示有，false表示没有
	 */
	private boolean hasChild;
	
	/**
	 * 子菜单列表
	 */
	private List<Resources> childMenu;

	public Resources getParentRes() {
		return parentRes;
	}

	public void setParentRes(Resources parentRes) {
		this.parentRes = parentRes;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<Resources> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<Resources> childMenu) {
		this.childMenu = childMenu;
	}
	
	
}
