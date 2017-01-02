package com.sjg.rts.service;

import com.sjg.rts.dao.AwardResultDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.sjg.rts.entity.AwardResult;

@Service
public class AwardResultService {

    @Resource
    private AwardResultDao awardResultDao;

    public int insert(AwardResult awardResult){
        return awardResultDao.insert(awardResult);
    }

    public int insertList(List< AwardResult> awardResults){
        return awardResultDao.insertList(awardResults);
    }

    public List<AwardResult> select(AwardResult awardResult){
        return awardResultDao.select(awardResult);
    }

    public int update(AwardResult awardResult){
        return awardResultDao.update(awardResult);
    }

}
