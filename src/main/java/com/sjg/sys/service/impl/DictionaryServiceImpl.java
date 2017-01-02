package com.sjg.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.DictionaryMapper;
import com.sjg.sys.entity.Dictionary;
import com.sjg.sys.entity.vo.DictionaryVo;
import com.sjg.sys.service.IDictionaryService;
@Service
public class DictionaryServiceImpl implements IDictionaryService {
@Resource
private DictionaryMapper dictionaryMapper;
	@Override
	public int delete(Long id) {
		return  dictionaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int save(Dictionary dictionary) {
		return  dictionaryMapper.insert(dictionary);
	}

	@Override
	public int update(Dictionary dictionary) {
		return  dictionaryMapper.updateByPrimaryKey(dictionary);
	}
	
	@Override
	public int updateSelective(Dictionary dictionary) {
		return  dictionaryMapper.updateByPrimaryKeySelective(dictionary);
	}

	@Override
	public Dictionary findById(Long id) {
		return  dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Dictionary> findByMulCond(Map<String, Object> parm) {
		return dictionaryMapper.findByMulCond(parm);
	}

	@Override
	public List<DictionaryVo> findByMulCondRelation(Map<String, Object> parm) {
		return dictionaryMapper.findByMulCondRelation(parm);
	}

	@Override
	public int deleteList(List list) {
		if(list==null || list.size()<=0){
			return 0;
		}else{
			return dictionaryMapper.deleteList(list);
		}
	}

	@Override
	public List<DictionaryVo> selectchildrenList(Long pid) {
		return dictionaryMapper.selectchildrenList(pid);
	}

	@Override
	public List<Dictionary> findByPCode(String pcode) {
		return dictionaryMapper.findByPCode(pcode);
	}

	@Override
	public Dictionary findByCode(String code) {
		return dictionaryMapper.findByCode(code);
	}

	@Override
	public List<Dictionary> checkCode(Long id, String code) {
		return dictionaryMapper.checkCode(id,code);
	}

	@Override
	public int updateNotNull(Dictionary dictionary) {
		return dictionaryMapper.updateByPrimaryKeySelective(dictionary);
	}

	@Override
	public List<Dictionary> findRecommendByCode(String code) {
		return dictionaryMapper.findRecommendByCode(code);
	}

	@Override
	public List<Dictionary> checkValue(Long id, String value) {
		return  dictionaryMapper.checkValue(id,value);
	}

}
