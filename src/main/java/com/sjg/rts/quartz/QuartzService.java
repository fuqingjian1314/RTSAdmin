package com.sjg.rts.quartz;

import com.alibaba.fastjson.JSON;
import com.sjg.rts.entity.Award;
import com.sjg.rts.entity.AwardData;
import com.sjg.rts.entity.AwardResult;
import com.sjg.rts.entity.memberResult;
import com.sjg.rts.service.AwardResultService;
import com.sjg.rts.service.BetorderService;
import com.sjg.rts.websocket.WebSocketService;
import com.sjg.sys.entity.User;
import com.sjg.sys.quartz.QuartzJobFactory;
import com.sjg.sys.quartz.ScheduleJob;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.HttpUtils;
import com.sjg.sys.utils.RedisUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class QuartzService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private AwardResultService awardResultService;
    @Autowired
    private BetorderService betorderService;
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private IUserService iUserService;

    public static int periodNumber_ori;

    public static long fullperiodNumber_nxt;
    /**
     * 添加定时任务
     *
     * @param job
     * @return
     * @throws SchedulerException
     */
    public String addOrUpdateQuartz(ScheduleJob job) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        if (null != job) {
            //获取触发器标识
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {//不存在任务
                //创建任务
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup())
                        .build();

                jobDetail.getJobDataMap().put("scheduleJob", job);

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(job.getJobName(), job.getJobGroup())
                        .withSchedule(scheduleBuilder)
                        .build();

                scheduler.scheduleJob(jobDetail, trigger);
            } else {//存在任务

                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder()
                        .withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder)
                        .build();

                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
            return "success";
        }

    public String pauseQuartz(ScheduleJob job) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.pauseJob(jobKey);
        return "success";
    }

    public String resumeQuartz(ScheduleJob job) throws SchedulerException {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.resumeJob(jobKey);
        return "success";
    }

    public String deleteQuartz(ScheduleJob job) throws SchedulerException {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.deleteJob(jobKey);
        return "success";
    }
    private  AwardData getAwardData(){
        double t=Math.random();
        String drawurl=(String) redisUtil.get("drawurl");
        String url=drawurl+"?t="+t;
        String str= HttpUtils.HttpGetRequest(url);
        System.out.println(str);
        AwardData awardData= JSON.parseObject(str,AwardData.class);
        return awardData;
    }
    /*循环8秒看是否跳期*/
    public Boolean isChangePeriods() throws SchedulerException {
        AwardData awardData=getAwardData();
        if(awardData==null){
            return false;
        }
        Award current=awardData.getCurrent();
        int periodNumber_cur=current.getPeriodNumber().intValue();
        //已经跳期
        if(periodNumber_cur-periodNumber_ori>=1){

            //到23期说明已经是晚上2点了，要等到10点才会开期
            if(periodNumber_cur==23){
                //删除以前的定时器
                ScheduleJob job = new ScheduleJob();
                job.setJobId("10001");
                job.setJobName("award_dataget");
                job.setJobGroup("dataWork");
                deleteQuartz(job);
                //到早晨9.52启动获取数据
                job.setJobName("dataget_begin");
                job.setJobStatus("1");
                job.setCronExpression("30 52 09 * * ?");//系统启动后3秒执行一次
                job.setDesc("数据导入任务");
                job.setFullClass("com.sjg.rts.quartz.QuartzService");
                job.setBeanName("quartzService");
                job.setDoMethod("test");
                this.addOrUpdateQuartz(job);
            }
            //重新开始倒计时
            Award next=awardData.getNext();
            int delayms=next.getAwardTimeInterval();
            int delayms_stopbet=next.getAwardTimeInterval();
            if(periodNumber_cur==120){
                delayms-=86400000;
                if(delayms<0){
                    return true;
                }
            }
            delayms+=next.getDelayTimeInterval()*1000;
            redisUtil.set("wjstopbet","no");
            try {
                changToDelay(delayms);
                changToStopBetTask(delayms_stopbet);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            //结果保存
            String[] r=current.getAwardNumbers().split(",");
            AwardResult awardResult=new AwardResult(Integer.parseInt(r[0]),Integer.parseInt(r[1]),Integer.parseInt(r[2]),Integer.parseInt(r[3]),Integer.parseInt(r[4]),current.getFullPeriodNumber(),current.getAwardTime());
            //从redis获取当前期数
            long fullperiodNumber_cur=Long.parseLong((String)redisUtil.get("fullperiodNumber_cur"));
            if(fullperiodNumber_cur<current.getFullPeriodNumber().longValue()){
                awardResultService.insert(awardResult);
            }
            if(periodNumber_cur==120){
                periodNumber_ori=0;
            }else{
                periodNumber_ori=periodNumber_cur;
            }
            redisUtil.set("fullperiodNumber_cur",String.valueOf(current.getFullPeriodNumber()));
            redisUtil.addList("drawstrs",awardData.toString());
            fullperiodNumber_nxt=current.getFullPeriodNumber()+1;
            try{
                //计算这一期的结果
                betorderService.callOrderResult(current.getFullPeriodNumber());
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                //推送到结果给用户
                List<memberResult> memberResults=betorderService.selectMemberResult(current.getFullPeriodNumber());
                //如果这期没有结果就不用推送了
                if(memberResults==null||memberResults.size()==0){
                    return true;
                }
                for (int i = 0; i < memberResults.size(); i++) {
                    memberResult memberResult=memberResults.get(i);
                    Session session=WebSocketService.onlineUserSessions.get(memberResult.getMemberId());
                    if (session == null) {
                        continue;
                    }
                    String userId=memberResult.getMemberId();
                    int hasPoits=Integer.parseInt((String)redisUtil.get("sys_user"+userId));
                    String resPoits=memberResult.getResult()+hasPoits+"";
                    User user=new User();
                    user.setId(Long.parseLong(userId));
                    user.setRestscore(new BigDecimal(memberResult.getResult()+hasPoits));
                    iUserService.updateNotNUll(user);
                    redisUtil.set("sys_user"+userId,resPoits);
                    session.getBasicRemote().sendText(resPoits);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }
        return true;
    }

    /**
     * 启动5秒循环获取
     * @return
     * @throws SchedulerException
     */
    public Boolean changeQuartzJob() throws SchedulerException {
        System.out.println("进入8秒循环");
        Long time=new Date().getTime()+1000;
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTimeInMillis(time);
        int h=calendar.get(java.util.Calendar.HOUR_OF_DAY);
        int m=calendar.get(java.util.Calendar.MINUTE);
        int s=calendar.get(java.util.Calendar.SECOND);
        String cron=String.format("%d/8 * * * * ?",s);
        ScheduleJob job = new ScheduleJob();
        job.setJobId("10001");
        job.setJobName("award_dataget");
        job.setJobGroup("dataWork");
        job.setJobStatus("1");
        job.setCronExpression(cron);
        job.setDesc("8秒轮训获取数据");
        job.setFullClass("com.sjg.rts.quartz.QuartzService");
        job.setBeanName("quartzService");
        job.setDoMethod("isChangePeriods");
        deleteQuartz(job);
        addOrUpdateQuartz(job);
        return true;
    }
    /**
     * 延迟过后去调changeQuartzJob：启动5秒循环获取
     */
    public void changToDelay(int delayms) throws SchedulerException {
        Long time=new Date().getTime()+delayms;
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTimeInMillis(time);
        int h=calendar.get(java.util.Calendar.HOUR_OF_DAY);
        int m=calendar.get(java.util.Calendar.MINUTE);
        int s=calendar.get(java.util.Calendar.SECOND);
        String cron=String.format("%d %d %d * * ?",s,m,h);
        System.out.println(cron+"##"+delayms/1000+"后运行");
        ScheduleJob job = new ScheduleJob();
        job.setJobId("10001");
        job.setJobName("award_dataget");
        job.setJobGroup("dataWork");
        job.setJobStatus("1");
        job.setCronExpression(cron);
        job.setDesc("获取数据倒计时");
        job.setFullClass("com.sjg.sys.quartz.QuartzService");
        job.setBeanName("quartzService");
        job.setDoMethod("changeQuartzJob");
        deleteQuartz(job);
        addOrUpdateQuartz(job);

    }
    public void changToStopBetTask(int delayms) throws SchedulerException {
        Long time = Long.valueOf((new Date()).getTime() + (long)delayms);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time.longValue());
        int h = calendar.get(11);
        int m = calendar.get(12);
        int s = calendar.get(13);
        String cron = String.format("%d %d %d * * ?", new Object[]{Integer.valueOf(s), Integer.valueOf(m), Integer.valueOf(h)});
        System.out.println(cron + "##" + delayms / 1000 + "后运行");
        ScheduleJob job = new ScheduleJob();
        job.setJobId("10002");
        job.setJobName("award_betstop");
        job.setJobGroup("dataWork");
        job.setJobStatus("1");
        job.setCronExpression(cron);
        job.setDesc("获取禁止下");
        job.setFullClass("com.sjg.sys.quartz.QuartzService");
        job.setBeanName("quartzService");
        job.setDoMethod("stopBet");
        this.deleteQuartz(job);
        this.addOrUpdateQuartz(job);
    }
    public void stopBet(){
//        if(!redisUtil.exists("wjstopbet")){
//            redisUtil.set("wjstopbet","yes");
//        }
        redisUtil.set("wjstopbet","yes");
    }
    //每10秒调一次
    public String test() throws SchedulerException {
        AwardData awardData_first=getAwardData();
        if(awardData_first!=null){//获取到数据
            Award current=awardData_first.getCurrent();
            Award next=awardData_first.getNext();
            boolean redisHasData=redisUtil.exists("fullperiodNumber_cur");
            if(!redisHasData){
                redisUtil.set("fullperiodNumber_cur",String.valueOf(current.getFullPeriodNumber()));
            }
            //从redis获取当前期数
            long fullperiodNumber_cur=Long.parseLong((String)redisUtil.get("fullperiodNumber_cur"));
            System.out.println("当前期数:"+fullperiodNumber_cur);
            //获取到了数据删除5秒循环job
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10001");
            job.setJobName("dataget_begin");
            job.setJobGroup("dataWork");
            deleteQuartz(job);

            //缓存到当前项目，简原期数
            periodNumber_ori=current.getPeriodNumber();
            //缓存到当前项目，全下一期
            fullperiodNumber_nxt=current.getFullPeriodNumber()+1;

            if((!redisHasData)||(fullperiodNumber_cur<current.getFullPeriodNumber().longValue())){//

                String[] r=current.getAwardNumbers().split(",");
                AwardResult awardResult=new AwardResult(Integer.parseInt(r[0]),Integer.parseInt(r[1]),Integer.parseInt(r[2]),Integer.parseInt(r[3]),Integer.parseInt(r[4]),current.getFullPeriodNumber(),current.getAwardTime());
                awardResultService.insert(awardResult);
                //保存当前期，缓存到redis
                redisUtil.set("fullperiodNumber_cur",String.valueOf(current.getFullPeriodNumber()));

                System.out.println("保存了期数");
            }

            //开始倒计时
            int delayms=next.getAwardTimeInterval();
            int delayms_stopbet=next.getAwardTimeInterval();
            delayms+=next.getDelayTimeInterval()*1000;

            if(delayms<0){
                //启动每隔5几秒看是否跳期
                changeQuartzJob();
                redisUtil.set("wjstopbet","yes");
            }else{
                //如果跳期开始倒计时，倒计时结束后开始5秒获取数据
                changToDelay(delayms);
                //等待延迟时间再开始5秒请求数据
                changToStopBetTask(delayms_stopbet);
            }
        }
        return null;
    }
}
