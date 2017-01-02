package com.sjg.sys.quartz;

import com.sjg.rts.quartz.QuartzService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sjg.sys.utils.SpringContextUtil;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/17.
 */
@Component
public class QuartzJobFactory implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException{
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]"+scheduleJob.getCronExpression());
//        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        ApplicationContext wac=SpringContextUtil.getApplicationContext();
        QuartzService qs= (QuartzService) wac.getBean(scheduleJob.getBeanName());
        try {
            Class clazz=qs.getClass();
//            Class<?> clazz = Class.forName(scheduleJob.getFullClass());
            // 调用TestReflect类中的reflect1方法
            Method method = clazz.getMethod(scheduleJob.getDoMethod());
//            method.invoke(clazz.newInstance());
            method.invoke(qs);
        }catch (Exception e){
            e.printStackTrace();
        }

        //根据name 与 group组成的唯一标识来判别该执行何种操作……
    }
}
