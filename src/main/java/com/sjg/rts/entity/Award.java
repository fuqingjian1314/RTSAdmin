package com.sjg.rts.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by fuqingjian on 2016/11/21.
 */
public class Award {
    Timestamp awardTime;
    Integer periodNumber;
    Long fullPeriodNumber;
    String periodNumberStr;
    Integer awardTimeInterval;
    String awardNumbers;
    Integer delayTimeInterval;
    Integer pan;
    Boolean isEnd;
    Integer nextMinuteInterval;
    Date periodDate;

    public Timestamp getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Timestamp awardTime) {
        this.awardTime = awardTime;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public Long getFullPeriodNumber() {
        return fullPeriodNumber;
    }

    public void setFullPeriodNumber(Long fullPeriodNumber) {
        this.fullPeriodNumber = fullPeriodNumber;
    }

    public String getPeriodNumberStr() {
        return periodNumberStr;
    }

    public void setPeriodNumberStr(String periodNumberStr) {
        this.periodNumberStr = periodNumberStr;
    }

    public Integer getAwardTimeInterval() {
        return awardTimeInterval;
    }

    public void setAwardTimeInterval(Integer awardTimeInterval) {
        this.awardTimeInterval = awardTimeInterval;
    }

    public String getAwardNumbers() {
        return awardNumbers;
    }

    public void setAwardNumbers(String awardNumbers) {
        this.awardNumbers = awardNumbers;
    }

    public Integer getDelayTimeInterval() {
        return delayTimeInterval;
    }

    public void setDelayTimeInterval(Integer delayTimeInterval) {
        this.delayTimeInterval = delayTimeInterval;
    }

    public Integer getPan() {
        return pan;
    }

    public void setPan(Integer pan) {
        this.pan = pan;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public Integer getNextMinuteInterval() {
        return nextMinuteInterval;
    }

    public void setNextMinuteInterval(Integer nextMinuteInterval) {
        this.nextMinuteInterval = nextMinuteInterval;
    }

    public Date getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date periodDate) {
        this.periodDate = periodDate;
    }

    @Override
    public String toString() {
        return "{" +
                "awardTime=" + awardTime +
                ", periodNumber=" + periodNumber +
                ", fullPeriodNumber=" + fullPeriodNumber +
                ", periodNumberStr='" + periodNumberStr + '\'' +
                ", awardTimeInterval=" + awardTimeInterval +
                ", awardNumbers='" + awardNumbers + '\'' +
                ", delayTimeInterval=" + delayTimeInterval +
                ", pan=" + pan +
                ", isEnd=" + isEnd +
                ", nextMinuteInterval=" + nextMinuteInterval +
                ", periodDate=" + periodDate +
                '}';
    }
}
