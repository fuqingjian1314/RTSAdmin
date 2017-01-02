package com.sjg.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.LogMapper;
import com.sjg.sys.entity.Log;
import com.sjg.sys.entity.vo.LogQueryParm;
import com.sjg.sys.service.ILogService;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;

@Service
public class LogServiceImpl implements ILogService{

	@Resource
	private LogMapper logMapper;
	
	@Override
	public int add(Log log) {
		return logMapper.insert(log);
	}

	@Override
	public Pagination<Log> getSystemLogPage(long offSet, long pageSize, String lrLoginName, String logType) {
		Map<String, Object> params = new HashMap<>();
		params.put("offSet", offSet);	
		params.put("pageSize", pageSize);	
		params.put("lrLoginName", lrLoginName);	
		params.put("logType", logType);	
		
		List<Log> list = logMapper.findSystemLogList(params);
		long count = logMapper.findCountByType(params);
		Pagination<Log> page=new Pagination<Log>(offSet,pageSize,count);
		page.setItems(list);
		return page;
	}

	@Override
	public List<Log> getLogListLimit(Long offSet, Long pageSize, LogQueryParm param) {
		Map<String, Object> params = new HashMap<>();
		params.put("offSet", offSet);	
		params.put("pageSize", pageSize);	
		params.put("lrLoginName", param.getLrLoginName());	
		params.put("logType", param.getLogType());
		params.put("createby", param.getCreateby());	
		params.put("occurStartTime", param.getOccurStartTime());	
		params.put("occurEndTime", param.getOccurEndTime());	
		List<Log> list = logMapper.findSystemLogList(params);
		for (Log log : list){
			if(log.getRequestIp() != null && !"".equals(log.getRequestIp())){
				if("0:0:0:0:0:0:0:1".equals(log.getRequestIp())){
					log.setRequestIp("127.0.0.1");
				}
			}
		}

		return list;
	}

	@Override
	public Pager<Log> getLogListPager(Long offSet, Long pageSize, LogQueryParm param) {
		Map<String, Object> params = new HashMap<>();
		params.put("offSet", offSet);	
		params.put("pageSize", pageSize);	
		params.put("lrLoginName", param.getLrLoginName());	
		params.put("logType", param.getLogType());
		params.put("createby", param.getCreateby());	
		params.put("occurStartTime", param.getOccurStartTime());	
		params.put("occurEndTime", param.getOccurEndTime());
		
		List<Log> list = logMapper.findSystemLogList(params);
		long count = logMapper.findCountByType(params);
		for (Log log : list){
			if(log.getRequestIp() != null && !"".equals(log.getRequestIp())){
				if("0:0:0:0:0:0:0:1".equals(log.getRequestIp())){
					log.setRequestIp("127.0.0.1");
				}
			}
		}
		Pager<Log> p=new Pager<Log>();
		p.setItems(list);
		p.setRowsCount(count);
		return p;
	}

	@Override
	public Log getLogDetailById(String id) {
		Log log = logMapper.selectByPrimaryKey(id);
		if(log.getRequestIp() != null && !"".equals(log.getRequestIp())){
			if("0:0:0:0:0:0:0:1".equals(log.getRequestIp())){
				log.setRequestIp("127.0.0.1");
			}
		}
		return log;
	}

}
