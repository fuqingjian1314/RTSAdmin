package com.sjg.rts.dao;

import com.sjg.rts.entity.memberResult;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.sjg.rts.entity.Betorder;

public interface BetorderDao {

    int insert(@Param("pojo") Betorder pojo);

    int insertList(@Param("pojos") List< Betorder> pojo);

    List<Betorder> select(@Param("pojo") Betorder pojo);

    List<Betorder> selectPageTody(@Param("pojo") Betorder pojo,@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("startbettime")String startbettime,@Param("endbettime")String endbettime);

    int update(@Param("pojo") Betorder pojo);

    void callOrderResult(@Param("fullPeriodNumber") Long fullPeriodNumber);

    List<memberResult> selectMemberResult(@Param("fullPeriodNumber") Long fullPeriodNumber);

}
