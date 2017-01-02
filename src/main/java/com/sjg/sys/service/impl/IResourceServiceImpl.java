package com.sjg.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.ResourcesMapper;
import com.sjg.sys.entity.Resources;
import com.sjg.sys.entity.Role;
import com.sjg.sys.entity.vo.ResourceMenuVo;
import com.sjg.sys.service.IResourceService;
import com.sjg.sys.utils.StringUtils;

@Service
public class IResourceServiceImpl implements IResourceService {

	@Resource
	private ResourcesMapper resourcesMapper;
	
	@Override
	public List<Resources> queryResources(String userId,String type,String pid) {
		Map<String, Object> map= new HashMap<>();
		if(StringUtils.isNullOrEmpty(userId)){
			return null;
		}
		if(StringUtils.isNullOrEmpty(type)){
			return null;
		}
		map.put("userId", userId);
		map.put("type", type);
		map.put("pid", pid);
		List<Resources> list = resourcesMapper.queryResourceByUserIdAndType(map);
		return list;
	}
	@Override
	public List<Resources> queryAllResources(long userId,long type) {
		List<Resources> list = resourcesMapper.queryAllResourceByUserIdAndType(userId,type);
		return list;
	}
	@Override
	public List<ResourceMenuVo> queryChildMenuList(String userId, String type, String pid) {
		
		Map<String, Object> map= new HashMap<>();
		if(StringUtils.isNullOrEmpty(userId) || StringUtils.isNullOrEmpty(type) || StringUtils.isNullOrEmpty(pid)){
			return null;
		}
		map.put("userId", userId);
		map.put("type", type);
		map.put("pid", pid);
		List<Resources> list = resourcesMapper.queryResourceByUserIdAndType(map);
		if(list == null || list.size()<1){
			return null;
		}
		List<ResourceMenuVo> rmvList = new ArrayList<>();
		for(Resources temp : list){
			ResourceMenuVo rmv =new ResourceMenuVo();
			rmv.setParentRes(temp);
			map.put("pid", temp.getId());
			Long num = resourcesMapper.queryCountByUserIdAndType(map);
			rmv.setHasChild(num != null && num>0);
			rmvList.add(rmv);
		}
		
		return rmvList;
	}

	@Override
	public int delete(Long id) {
		return resourcesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int save(Resources resources) {
		return resourcesMapper.insert(resources);
	}
	@Override
	public void saves(List<Resources> resourcess) {
		if(resourcess==null){
			return;
		}
		for (Resources resources : resourcess) {
			resourcesMapper.insert(resources);
		}
	}
	@Override
	public int update(Resources resources) {
		return resourcesMapper.updateByPrimaryKeySelective(resources);
	}
	@Override
	public void updatesisHide(List<Resources> resourcess) {
		for (Resources resources : resourcess) {
			resources.setIsHide(0);
			resourcesMapper.updateByPrimaryKeySelective(resources);
		}
	}
	@Override
	public void updatesisNotHide(List<Resources> resourcess) {
		for (Resources resources : resourcess) {
			resources.setIsHide(1);
			resourcesMapper.updateByPrimaryKeySelective(resources);
		}
	}
	@Override
	public Resources findById(Long id) {
		return resourcesMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Resources> findByPidAndType(Long pid,Long type) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("type", type);
		return resourcesMapper.findByPidAndType(map);
	}
	@Override
	public List<Resources> findAllByPidAndType(Long pid,Long type) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("type", type);
		return resourcesMapper.findAllByPidAndType(map);
	}
	@Override
	public List<Resources> queryResourcesListByRole(long roleId){
		Role role=new Role();
		role.setId(roleId);
		return resourcesMapper.queryResourcesListByRole(role);
		
	}
	@Override
	public int deleteList(List list) {
		if(list==null || list.size()<=0){
			return 0;
		}else{
			return resourcesMapper.deleteList(list);
		}
	}
	@Override
	public int deleteRealList(List<Resources> list) {
		if(list==null || list.size()<=0){
			return 0;
		}else{
			return resourcesMapper.deleteRealList(list);
		}
	}
}
