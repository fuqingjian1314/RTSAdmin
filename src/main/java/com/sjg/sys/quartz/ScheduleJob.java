package com.sjg.sys.quartz;

/**
 * 计划任务信息
 * @author unique
 */
public class ScheduleJob {
    /** 任务id */
    private String jobId;
    /** 任务名称 */
    private String jobName;
    /** 任务分组 */
    private String jobGroup;
    /** 任务状态 0禁用 1启用 2删除*/
    private String jobStatus;
    /** 任务运行时间表达式 */
    private String cronExpression;
    /** 任务描述 */
    private String desc;
    /**spring的bean名称**/
    private String beanName;
    /**类全路径**/
    private String fullClass;
    /**调用方法**/
    private String doMethod;
    public ScheduleJob(){
        super();
    }
    public ScheduleJob(String jobId, String jobName, String jobGroup,
                       String jobStatus, String cronExpression, String desc,String beanName,String fullClass,String doMethod) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobStatus = jobStatus;
        this.cronExpression = cronExpression;
        this.desc = desc;
        this.beanName=beanName;
        this.fullClass=fullClass;
        this.doMethod=doMethod;
    }
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getJobGroup() {
        return jobGroup;
    }
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    public String getCronExpression() {
        return cronExpression;
    }
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getFullClass() {
        return fullClass;
    }

    public void setFullClass(String fullClass) {
        this.fullClass = fullClass;
    }

    public String getDoMethod() {
        return doMethod;
    }

    public void setDoMethod(String doMethod) {
        this.doMethod = doMethod;
    }
}
