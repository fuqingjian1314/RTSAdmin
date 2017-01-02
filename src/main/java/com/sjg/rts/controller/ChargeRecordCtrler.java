package com.sjg.rts.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjg.rts.entity.ChargeRecordVO;
import com.sjg.rts.service.ChargeRecordService;
import com.sjg.sys.utils.Pagination;

/**
 * 控制器类.
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/chargeRecorder")
public class ChargeRecordCtrler{
	/**
	 * 注入的业务处理类.
	 */
	@Autowired
	private ChargeRecordService chargeRecordService;

	/**
	 * 日志记录类.
	 */
	private Logger log = LoggerFactory.getLogger(ChargeRecordCtrler.class);

	/**
	 * 新增业务数据保存方法.
	 * @param vo 业务数据
	 */
	@ResponseBody
	public void save(ChargeRecordVO vo) {
		log.info("save");
		chargeRecordService.save(vo);
	}

	/**
	 * 删除选中的数据.
	 * @param ids 选中的数据列表，逗号分隔
	 */
	@ResponseBody
	public void delete(String ids) {
		String[] arr = ids.split(",");
		chargeRecordService.delete(arr);
	}

	/**
	 * 修改业务数据.
	 * @param vo 修改后的业务数据
	 */
	@ResponseBody
	public void update(ChargeRecordVO vo) {
		log.info("update");
		chargeRecordService.update(vo);
	}

	/**
	 * 根据页面输入查询条件，分页查询数据.
	 * @param vo 业务查询参数
	 * @param page 分页对象数据
	 * @return 业务数据及满足查询条件的总记录数
	 */
	@ResponseBody
	public Map<String, Object> query(ChargeRecordVO vo, Pagination page) {
		log.info("list");
		Map<String, Object> map = chargeRecordService.findPageInfos(vo, page);
		return map;
	}

	/**
	 * 功能模块入口方法，返回功能模块主界面
	 * @return 功能模块页面
	 */
	public String list() {
		return "tools/RtsChargeRecordList";
	}

	/**
	 * 展现业务信息详情页面
	 * @return 业务信息详情编辑页面
	 */
	public String detail() {
		return "tools/RtsChargeRecordForm";
	}

	/**
	 * 根据主键，获取主键对应详细数据
	 * @param id 业务主键
	 * @return 业务主键对应业务详细数据
	 */
	@ResponseBody
	public ChargeRecordVO getChargeRecordVOById(String id) {
		return chargeRecordService.getChargeRecordVOById(id);
	}
}
