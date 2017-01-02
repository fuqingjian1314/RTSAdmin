package com.sjg.rts.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sjg.rts.dao.ChargeRecordDao;
import com.sjg.rts.entity.ChargeRecordVO;
import com.sjg.rts.service.ChargeRecordService;
import com.sjg.sys.utils.Pagination;

/**
 * 服务层接口实现类
 * @author Administrator
 * 
 */
@Service("chargeRecordService")
public class ChargeRecordServiceImpl implements ChargeRecordService {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(ChargeRecordServiceImpl.class);

	/**
	 * 依赖DAO层接口类
	 */
	@Autowired
	private ChargeRecordDao chargeRecordDao;

	@Override
	public void delete(String[] arr) {
		logger.info("iRtsChargeRecordService delete");
		chargeRecordDao.delete(arr);

	}

	@Override
	public Map<String, Object> findPageInfos(ChargeRecordVO vo, Pagination page) {
		logger.info("iRtsChargeRecordService findAllInfos");

		Map<String, Object> map = new HashMap<String, Object>();
//		List<ChargeRecordVO> result = chargeRecordDao.findAllInfos(vo, page.getPageIndex() * page.getPageSize(),
//				page.getPageSize());
//		Integer total = chargeRecordDao.findAllInfosCount(vo);
//		//返回给action层的数据结果集以及总的数据量，这里默认的使用data、total的key，
//		//action层可以根据页面定义的不同来重新封装
//		map.put("data", result);
//		map.put("total", total);
		return map;
	}
	@Override
	public List<Map<String, Object>> findAllInfos(ChargeRecordVO vo) {
		logger.info("iRtsChargeRecordService findAllInfos");
		
		List<Map<String, Object>> result = chargeRecordDao.findAllInfos(vo);
		return result;
	}

	@Override
	public void save(ChargeRecordVO vo) {
		logger.info("iRtsChargeRecordService save");
		chargeRecordDao.addChargeScoreToRestScore(vo);
		chargeRecordDao.save(vo);
	}

	@Override
	public void update(ChargeRecordVO vo) {
		logger.info("iRtsChargeRecordService update");
		chargeRecordDao.update(vo);
	}

	@Override
	public ChargeRecordVO getChargeRecordVOById(String id) {
		logger.info("iRtsChargeRecordService getChargeRecordVOById");
		return chargeRecordDao.getChargeRecordVOById(id);
	}

}
