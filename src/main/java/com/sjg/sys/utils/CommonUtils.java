package com.sjg.sys.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.sjg.sys.entity.User;

/**
 * 公共
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 */
public class CommonUtils {
	
	
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}
	/**
	 * 得到登录用户
	 * @return
	 */
	public static User getSessionUser(){
		User user = (User) CommonUtils.getSession().getAttribute(contantUtil.SESSION_USER);
		return user;
	}
	
	
	
	/**
	 * 判断是否POST提交，是POST返回true，不是POST提交返回false
	 * 
	 * @return 是POST返回true，不是POST提交返回false
	 */
	public static Boolean isPost() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if ("POST".equals(request.getMethod())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * AJAX提交返回信息
	 * 
	 * @param status
	 *            是否成功，true成功，false 异常
	 * @param msg
	 *            返回信息提示
	 * @param data
	 *            
	 * @return 返回信息
	 */
	public static String msgCallback(Boolean status, String info, String url, String data) {
		Map<String, Object> callback = new HashMap<String, Object>();
		callback.put("status", status);
		callback.put("info", info);
		callback.put("url", url);
		callback.put("data", data);
		return JSONArray.toJSONString(callback);
	}
	
	/**
	 * AJAX提交返回信息
	 * @author wenjie 
	 * @param status 是否成功，true成功，false 异常
	 * @param info   返回信息提示
	 * @param data   返回数据
	 * @return
	 */
	public static String msgCallback(Boolean status, String info, Object data) {
		Map<String, Object> callback = new HashMap<String, Object>();
		callback.put("status", status);
		callback.put("info", info);
		callback.put("data", data);
		return JSONArray.toJSONString(callback);
	}
	public static String msgCallback(Boolean status, String info) {
		Map<String, Object> callback = new HashMap<String, Object>();
		callback.put("status", status);
		callback.put("info", info);
		return JSONArray.toJSONString(callback);
	}
	/**
	 * String转换double
	 * 
	 * @param string
	 * @return double
	 */
	public static double convertSourData(String dataStr) throws Exception {
		if (dataStr != null && !"".equals(dataStr)) {
			return Double.valueOf(dataStr);
		}
		throw new NumberFormatException("convert error!");
	}

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return false;
		} else {
			return true;
		}
	}
	

	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}

	/**
	 * 保留两个小数
	 * 
	 * @return
	 */
	public static String formatDouble(Double b) {
		BigDecimal bg = new BigDecimal(b);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return String
	 */
	public static String fromDateH() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(new Date());
	}

	
	/**
	 * 返回当前时间　格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String fromDateY() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}

	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 	将Double类型的科学计数值转化为常规数值
	 * @author NEMO
	 * @param num
	 * @return
	 */
	public static Double scientificCountToDouble(Double num){
		DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();
		df.setMaximumFractionDigits(4);
		String temp = df.format(num);
		temp = temp.replace(",", "");
		Double doubleNum = Double.valueOf(temp);
		return doubleNum;
	}
	
}
