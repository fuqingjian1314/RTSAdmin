package com.sjg.sys.utils;


import com.alibaba.fastjson.JSON;
import com.sjg.rts.entity.AwardData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;


public class TestUrl1 {

	
	public static void main(String[] args) {
		double t=Math.random();
		String url="http://ssccrr.com/shishicai/getCqsscAwardData?t="+t;
//		String url="http://www.801140.com/shishicai/getCqsscAwardData.php?t="+t;
		String str=HttpUtils.HttpGetRequest(url);
		System.out.println(str);
//		String url="http://172.16.3.109:8080/user/user_webservice_json.do?";
//		String str=HttpUtils.HttpPostRequest(url,"jsonData={\"app\":\"CRM\",\"pKey\":\"DGGERP962540ADMIN\",\"erp_req_parameter_util\":\"U01\",\"lastdate\":\"2016-11-30 14:22:00\"}");
//		System.out.println(str);
//		String str=HttpUtils.doGet(url, null, "utf-8", false);
//		AwardData awardData= JSON.parseObject(str,AwardData.class);
//		System.out.println(awardData.getNext().toString());
//		Long time=new Date().getTime()+awardData.getNext().getAwardTimeInterval()+awardData.getNext().getDelayTimeInterval()*1000;
//		GregorianCalendar calendar=new GregorianCalendar();
//		calendar.setTimeInMillis(time);
//		int h=calendar.get(java.util.Calendar.HOUR_OF_DAY);
//		int m=calendar.get(java.util.Calendar.MINUTE);
//		int s=calendar.get(java.util.Calendar.SECOND);
//		System.out.println(h+":"+m+":"+s);
//		BigDecimal b=new BigDecimal(10);
//		System.out.println(String.valueOf(b));



	}

}


