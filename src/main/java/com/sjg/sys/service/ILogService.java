package com.sjg.sys.service;

import java.util.List;

import com.sjg.sys.entity.Log;
import com.sjg.sys.entity.vo.LogQueryParm;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;

/**
 * 日志service接口
 * @author Hel
 *
 */
public interface ILogService {

	/**
	 * 保存登录信息
	 * @param log
	 * @return 返回sql受影响的行
	 */
	int add(Log log);
	
	/**
	 * 分页查询系统日志
	 * @param offSet     起始行标
	 * @param pageSize	显示数据的条数
	 * @param lrLoginName	登录名
	 * @param logType 日志类型 （0:操作日志；1:系统日志）
	 * @return
	 */
	Pagination<Log> getSystemLogPage(long offSet, long pageSize,String lrLoginName, String logType) ;
	
	/**
	 * 分页查询日志信息
	 * @param offSet
	 * @param pageSize
	 * @param param
	 * @return
	 */
	List<Log> getLogListLimit(Long offSet, Long pageSize, LogQueryParm param);
	
	/**
	 * 分页查询日志信息(初始化)
	 * @param offSet
	 * @param pageSize
	 * @param param
	 * @return
	 */
	Pager<Log> getLogListPager(Long offSet, Long pageSize, LogQueryParm param);
	
	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	Log getLogDetailById(String id);
	
	
}
