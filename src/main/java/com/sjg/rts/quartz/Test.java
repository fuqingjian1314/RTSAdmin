package com.sjg.rts.quartz;

import com.sjg.sys.quartz.ScheduleJob;
import com.sjg.sys.utils.RedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class Test implements InitializingBean {
    @Autowired
    private QuartzService quartzService;
    @Autowired
    private RedisUtil redisUtil;
    //只需要初始化一次
    private static boolean ISINIT=false;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (ISINIT) {
            return;
        }
        //初始化获取数据，如果获取到了数据这个job会被删除，如果没有继续获取数据
        ScheduleJob job = new ScheduleJob();
        job.setJobId("10001");
        job.setJobName("dataget_begin");
        job.setJobGroup("dataWork");
        job.setJobStatus("1");
        job.setCronExpression("30/10 * * * * ?");//系统启动后3秒执行一次
        job.setDesc("数据导入任务");
        job.setFullClass("com.sjg.rts.quartz.QuartzService");
        job.setBeanName("quartzService");
        job.setDoMethod("test");
//        quartzService.deleteQuartz(job);
        quartzService.addOrUpdateQuartz(job);
        ISINIT=true;
        System.out .println ("----------------------------数据获取任务初始化成功!---------------------------------" );
    }
}
