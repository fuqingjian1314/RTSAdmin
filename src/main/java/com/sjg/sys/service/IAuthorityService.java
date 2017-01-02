package com.sjg.sys.service;

import java.util.List;

import com.sjg.sys.entity.RoleResources;

/**
 * @author Administrator
 *
 */

public interface IAuthorityService {
	
	public void modifyRoleMenus(List<RoleResources> rrs,Long roleId);
	
}
