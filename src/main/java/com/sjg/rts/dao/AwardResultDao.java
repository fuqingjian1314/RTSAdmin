package com.sjg.rts.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.sjg.rts.entity.AwardResult;

public interface AwardResultDao {

    int insert(@Param("awardResult") AwardResult awardResult);

    int insertList(@Param("awardResults") List< AwardResult> awardResult);

    List<AwardResult> select(@Param("awardResult") AwardResult awardResult);

    int update(@Param("awardResult") AwardResult awardResult);

}
