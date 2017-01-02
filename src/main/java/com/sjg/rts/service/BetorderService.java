package com.sjg.rts.service;

import com.sjg.rts.dao.BetorderDao;
import com.sjg.rts.entity.memberResult;
import com.sjg.sys.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.sjg.rts.entity.Betorder;

@Service
public class BetorderService {

    @Resource
    private BetorderDao betorderDao;

    public int insert(Betorder pojo){
        return betorderDao.insert(pojo);
    }

    public int insertList(List< Betorder> pojos){
        return betorderDao.insertList(pojos);
    }

    public List<Betorder> select(Betorder pojo){
        return betorderDao.select(pojo);
    }
    public List<Betorder> selectPage(Betorder betorder,int offeset,int pagesize){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate=new Date();
        Date nextDate=new Date();
        //0-24点是获取当前日期和下一天2点，当超过24点就是获取的上一天日期到当天的2点
        Calendar calendar1 = Calendar.getInstance(); //得到日历
        calendar1.setTime(nowDate);//把当前时间赋给日历
        calendar1.add(Calendar.DAY_OF_MONTH, +1);  //设置为后是一天
        nextDate = calendar1.getTime();   //得到后一天的时间

        int hours=nowDate.getHours();//当天的小时数
        if(hours<2){
            nextDate=nowDate;
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(nowDate);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
            nowDate = calendar.getTime();   //得到前一天的时间
        }
        String commonTime = format.format(nowDate);
        String nextTime = format.format(nextDate);
        String startTime = commonTime + " 08:00:00";
        String endTime = nextTime + " 02:00:00";
        return betorderDao.selectPageTody(betorder,offeset,pagesize,startTime,endTime);
    }

    public int update(Betorder pojo){
        return betorderDao.update(pojo);
    }

    public void  callOrderResult(Long fullPeriodNumber){
        betorderDao.callOrderResult(fullPeriodNumber);
    }
    public List<memberResult> selectMemberResult(@Param("fullPeriodNumber") Long fullPeriodNumber){
        return betorderDao.selectMemberResult(fullPeriodNumber);
    }

}
