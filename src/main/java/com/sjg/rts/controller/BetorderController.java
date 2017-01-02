package com.sjg.rts.controller;

import com.alibaba.fastjson.JSONArray;
import com.sjg.rts.entity.Betorder;
import com.sjg.rts.quartz.QuartzService;
import com.sjg.rts.service.BetorderService;
import com.sjg.rts.websocket.WebSocketService;
import com.sjg.sys.annotation.FuncDescLog;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.RedisUtil;
import com.sjg.sys.utils.StringUtils;
import com.sjg.sys.utils.contantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

/**
 * Created by fuqingjian on 2016/12/1.
 */
@Controller
@RequestMapping("betorder")
public class BetorderController {
    @Autowired
    private BetorderService betorderService;
    @Resource
    private IUserService iUserService;
    @Autowired
    private RedisUtil redisUtil;
    @FuncDescLog(desc = "今日历史数据查询")
    @RequestMapping("queryBetOrders")
    @ResponseBody
    public String queryBetOrders(Betorder betorder, Integer offset, Integer pagesize , HttpServletRequest request){
        User user= (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
        if(betorder==null){
            betorder=new Betorder();
            betorder.setCjyhid(String.valueOf(user.getId()));
        }else if(betorder.getCjyhid()==null){
            betorder.setCjyhid(String.valueOf(user.getId()));
        }
        List<Betorder> betorders=betorderService.selectPage(betorder,offset,pagesize);
        return JSONArray.toJSONString(betorders);
    }
    @ResponseBody
    @RequestMapping("saveBetOrder")
    public String saveBetOrder(String betordersstr,String userid,HttpServletRequest request) throws IOException {
        if(org.apache.commons.lang.StringUtils.isBlank(userid)){
            userid="13";
        }
        String rtnStr="";
        if(QuartzService.fullperiodNumber_nxt==0){
            return CommonUtils.msgCallback(false,"无期数保存失败！");
        }
        String isStop=(String) redisUtil.get("wjstopbet");
        if("yes".equals(isStop)){
            return CommonUtils.msgCallback(false,"已经封盘！");
        }
        int sumCost=0;
        List<Betorder> betorders= JSONArray.parseArray(betordersstr,Betorder.class);
        for (Betorder betorder:betorders) {
            betorder.setFullPeriodNumber(QuartzService.fullperiodNumber_nxt);
            betorder.setCreateTime(new Timestamp(new Date().getTime()));
            betorder.setBettime(new Timestamp(new Date().getTime()));
            betorder.setCjyhid(userid);
            sumCost+=betorder.getCost().intValue();
        }
        int restPoits=Integer.parseInt(String.valueOf(redisUtil.get("sys_user"+userid)));
        System.err.println(restPoits);
        restPoits=restPoits-sumCost;
        System.err.println(restPoits);
        if(restPoits<0){
            return CommonUtils.msgCallback(false,"剩余分不够！");
        }
        betorderService.insertList(betorders);
        User user= (User) request.getSession().getAttribute(contantUtil.SESSION_USER);
        user.setRestscore(new BigDecimal(restPoits));
        iUserService.updateNotNUll(user);
        redisUtil.set("sys_user"+userid,String.valueOf(restPoits));
        request.getSession().setAttribute(contantUtil.SESSION_USER,user);
        Session session=WebSocketService.onlineUserSessions.get(userid);
        session.getBasicRemote().sendText(String.valueOf(restPoits));
        rtnStr= CommonUtils.msgCallback(true,"保存成功");
        return rtnStr;
    }
}
