package com.sjg.rts.entity;

import com.sjg.rts.util.RTSConstants;
import org.apache.commons.lang.StringUtils;
import com.sjg.rts.util.ComputationRule;

import java.sql.Timestamp;
import java.util.Date;


/**
 * Created by fuqingjian on 2016/11/21.
 */
public class AwardResult {
    private Long fullPeriodNumber;
    private Timestamp awardTime;
    private Integer first;
    private Integer second;
    private Integer third;
    private Integer fourth;
    private Integer fifth;
    private String firstDx;//single,double
    private String secondDx;
    private String thirdDx;
    private String fourthDx;
    private String fifDx;
    private String firstDs;//big,small
    private String secondDs;
    private String thirdDs;
    private String fourthDs;
    private String fifDs;
    private String sumDx;//sumbig,sumsmall
    private String sumDs;//sumsingle,sumdouble
    private String sumLfh;//dragon tiger peace
    private String beforesum5;//befleopard befstraight befpair befhalfstraight befmix6
    private String middlesum5;//midleopard minstraight minpair minhalfstraight minmix6
    private String aftersum5;//aftleopard aftstraight aftpair afthalfstraight aftmix6
    private Timestamp createTime;


    public AwardResult(Integer first,Integer second,Integer third,Integer fourth,Integer fifth,Long fullPeriodNumber,Timestamp awardTime) {
        this.first=first;
        this.second=second;
        this.third=third;
        this.fourth=fourth;
        this.fifth=fifth;
        setSingleBallResult();
        setSUMBallResult();
        this.createTime=new Timestamp(new Date().getTime());
        this.fullPeriodNumber=fullPeriodNumber;
        this.awardTime=awardTime;
    }

    public Long getFullPeriodNumber() {
        return fullPeriodNumber;
    }

    public void setFullPeriodNumber(Long fullPeriodNumber) {
        this.fullPeriodNumber = fullPeriodNumber;
    }

    public Timestamp getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Timestamp awardTime) {
        this.awardTime = awardTime;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public Integer getFourth() {
        return fourth;
    }

    public void setFourth(Integer fourth) {
        this.fourth = fourth;
    }

    public Integer getFifth() {
        return fifth;
    }

    public void setFifth(Integer fifth) {
        this.fifth = fifth;
    }

    public String getFirstDx() {
        return firstDx;
    }

    public void setFirstDx(String firstDx) {
        this.firstDx = firstDx;
    }

    public String getSecondDx() {
        return secondDx;
    }

    public void setSecondDx(String secondDx) {
        this.secondDx = secondDx;
    }

    public String getThirdDx() {
        return thirdDx;
    }

    public void setThirdDx(String thirdDx) {
        this.thirdDx = thirdDx;
    }

    public String getFourthDx() {
        return fourthDx;
    }

    public void setFourthDx(String fourthDx) {
        this.fourthDx = fourthDx;
    }

    public String getFifDx() {
        return fifDx;
    }

    public void setFifDx(String fifDx) {
        this.fifDx = fifDx;
    }

    public String getFirstDs() {
        return firstDs;
    }

    public void setFirstDs(String firstDs) {
        this.firstDs = firstDs;
    }

    public String getSecondDs() {
        return secondDs;
    }

    public void setSecondDs(String secondDs) {
        this.secondDs = secondDs;
    }

    public String getThirdDs() {
        return thirdDs;
    }

    public void setThirdDs(String thirdDs) {
        this.thirdDs = thirdDs;
    }

    public String getFourthDs() {
        return fourthDs;
    }

    public void setFourthDs(String fourthDs) {
        this.fourthDs = fourthDs;
    }

    public String getFifDs() {
        return fifDs;
    }

    public void setFifDs(String fifDs) {
        this.fifDs = fifDs;
    }

    public String getSumDx() {
        return sumDx;
    }

    public void setSumDx(String sumDx) {
        this.sumDx = sumDx;
    }

    public String getSumDs() {
        return sumDs;
    }

    public void setSumDs(String sumDs) {
        this.sumDs = sumDs;
    }

    public String getSumLfh() {
        return sumLfh;
    }

    public void setSumLfh(String sumLfh) {
        this.sumLfh = sumLfh;
    }

    public String getBeforesum5() {
        return beforesum5;
    }

    public void setBeforesum5(String beforesum5) {
        this.beforesum5 = beforesum5;
    }

    public String getMiddlesum5() {
        return middlesum5;
    }

    public void setMiddlesum5(String middlesum5) {
        this.middlesum5 = middlesum5;
    }

    public String getAftersum5() {
        return aftersum5;
    }

    public void setAftersum5(String aftersum5) {
        this.aftersum5 = aftersum5;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * singleball
     */
    public void setSingleBallResult() {
        if (this.first!=null) {
            if (ComputationRule.isDoubleNum(this.first)) {
                this.setFirstDs(RTSConstants.SINGLEDOUBLE);
            } else {
                this.setFirstDs(RTSConstants.SINGLESINGLE);
            }

            if (ComputationRule.isSingleBigNum(this.first)) {
                this.setFirstDx(RTSConstants.SINGLEBIG);
            } else {
                this.setFirstDx(RTSConstants.SINGLESMALL);
            }
        }
        if (this.second!=null) {
            if (ComputationRule.isDoubleNum(this.second)) {
                this.setSecondDs(RTSConstants.SINGLEDOUBLE);
            } else {
                this.setSecondDs(RTSConstants.SINGLESINGLE);
            }

            if (ComputationRule.isSingleBigNum(this.second)) {
                this.setSecondDx(RTSConstants.SINGLEBIG);
            } else {
                this.setSecondDx(RTSConstants.SINGLESMALL);
            }
        }

        if (this.third!=null) {
            if (ComputationRule.isDoubleNum(this.third)) {
                this.setThirdDs(RTSConstants.SINGLEDOUBLE);
            } else {
                this.setThirdDs(RTSConstants.SINGLESINGLE);
            }

            if (ComputationRule.isSingleBigNum(this.third)) {
                this.setThirdDx(RTSConstants.SINGLEBIG);
            } else {
                this.setThirdDx(RTSConstants.SINGLESMALL);
            }
        }
        if (this.fourth!=null) {
            if (ComputationRule.isDoubleNum(this.fourth)) {
                this.setFourthDs(RTSConstants.SINGLEDOUBLE);
            } else {
                this.setFourthDs(RTSConstants.SINGLESINGLE);
            }

            if (ComputationRule.isSingleBigNum(this.fourth)) {
                this.setFourthDx(RTSConstants.SINGLEBIG);
            } else {
                this.setFourthDx(RTSConstants.SINGLESMALL);
            }
        }
        if (this.fifth!=null) {
            if (ComputationRule.isDoubleNum(this.fifth)) {
                this.setFifDs(RTSConstants.SINGLEDOUBLE);
            } else {
                this.setFifDs(RTSConstants.SINGLESINGLE);
            }
            if (ComputationRule.isSingleBigNum(this.fifth)) {
                this.setFifDx(RTSConstants.SINGLEBIG);
            } else {
                this.setFifDx(RTSConstants.SINGLESMALL);
            }
        }

    }
    /**
     * sumball
     */
    public void setSUMBallResult() {
        if (this.first==null|| this.second==null||this.third==null||this.fourth==null||this.fifth==null) {
            return;
        }
        Integer sumValue = this.first + this.second + this.third + this.fourth + this.fifth;
        if (ComputationRule.isDoubleNum(sumValue)) {
            this.setSumDs(RTSConstants.SUMDOUBLE);
        } else {
            this.setSumDs(RTSConstants.SUMSINGLE);
        }
        if (ComputationRule.isSUMBigNum(sumValue)) {
            this.setSumDx(RTSConstants.SUMBIG);
        } else {
            this.setSumDx(RTSConstants.SUMSMALL);
        }
        if (this.first > this.fifth) {
            this.setSumLfh(RTSConstants.SUMDRAGON);
        } else if (this.first < this.fifth) {
            this.setSumLfh(RTSConstants.SUMTIGER);
        } else {
            this.setSumLfh(RTSConstants.SUMPEACE);
        }

        if (ComputationRule.isSUMLeopardQ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setBeforesum5(RTSConstants.SUMLEOPARDQ3);
        } else if (ComputationRule.isSUMStraightQ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setBeforesum5(RTSConstants.SUMSTRAIGHTQ3);
        } else if (ComputationRule.isSUMPairedQ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setBeforesum5(RTSConstants.SUMPAIREDQ3);
        } else if (ComputationRule.isSUMHalfstraightQ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setBeforesum5(RTSConstants.SUMHALFSTRAIGHTQ3);
        } else {
            this.setBeforesum5(RTSConstants.SUMMISCSIXQ3);
        }

        if (ComputationRule.isSUMLeopardZ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setMiddlesum5(RTSConstants.SUMLEOPARDZ3);
        } else if (ComputationRule.isSUMStraightZ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setMiddlesum5(RTSConstants.SUMSTRAIGHTZ3);
        } else if (ComputationRule.isSUMPairedZ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setMiddlesum5(RTSConstants.SUMPAIREDZ3);
        } else if (ComputationRule.isSUMHalfstraightZ3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setMiddlesum5(RTSConstants.SUMHALFSTRAIGHTZ3);
        } else {
            this.setMiddlesum5(RTSConstants.SUMMISCSIXZ3);
        }

        if (ComputationRule.isSUMLeopardH3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setAftersum5(RTSConstants.SUMLEOPARDH3);
        } else if (ComputationRule.isSUMStraightH3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setAftersum5(RTSConstants.SUMSTRAIGHTH3);
        } else if (ComputationRule.isSUMPairedH3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setAftersum5(RTSConstants.SUMPAIREDH3);
        } else if (ComputationRule.isSUMHalfstraightH3(this.first, this.second, this.third, this.fourth, this.fifth)) {
            this.setAftersum5(RTSConstants.SUMHALFSTRAIGHTH3);
        } else {
            this.setAftersum5(RTSConstants.SUMMISCSIXH3);
        }

    }

}

