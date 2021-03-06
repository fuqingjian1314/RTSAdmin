package com.sjg.sys.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/quartz")
public class QuartzController {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
/*
@Autowired
private IQuartzBS quartzBS;
*/

    /**
     * 任务创建与更新(未存在的就创建，已存在的则更新)
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/update", method={RequestMethod.POST,RequestMethod.GET})
    public String updateQuartz(HttpServletRequest request,HttpServletResponse response,
                               @ModelAttribute("scheduleJob") ScheduleJob job,ModelMap model){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if(null!=job){
                //获取触发器标识
                TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
                //获取触发器trigger
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                if(null==trigger){//不存在任务
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

                    //把任务插入数据库
//                    int result = quartzBS.add(job);
//                    if(result!=0){
//                        model.addAttribute("msg", "您的任务创建成功！");
//                    }else{
//                        model.addAttribute("msg", "您的任务创建失败！");
//                    }
                }else{//存在任务

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

                    //更新数据库中的任务
//                    int result = quartzBS.update(job);
//                    if(result==1){
//                        model.addAttribute("msg", "您的任务更新成功！");
//                    }else{
//                        model.addAttribute("msg", "您的任务更新失败！");
//                    }
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "/warn.jsp";
    }

    /**
     * 暂停任务
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/pause", method={RequestMethod.POST,RequestMethod.GET})
    public String pauseQuartz(HttpServletRequest request,HttpServletResponse response,
                              @ModelAttribute("scheduleJob") ScheduleJob scheduleJob,ModelMap model){

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return "/warn.jsp";
    }
    /**
     * 恢复任务
     * @param request
     * @param response
     * @param scheduleJob
     * @param model
     * @return
     */
    @RequestMapping(value="/resume", method={RequestMethod.POST,RequestMethod.GET})
    public String resumeQuartz(HttpServletRequest request,HttpServletResponse response,
                               @ModelAttribute("scheduleJob") ScheduleJob scheduleJob,ModelMap model){

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return "/warn.jsp";
    }
    /**
     * 删除任务
     * @param request
     * @param response
     * @param scheduleJob
     * @param model
     * @return
     */
    @RequestMapping(value="/delete", method={RequestMethod.POST,RequestMethod.GET})
    public String deleteQuartz(HttpServletRequest request,HttpServletResponse response,
                               @ModelAttribute("scheduleJob") ScheduleJob scheduleJob,ModelMap model){

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "/warn.jsp";
    }
}
