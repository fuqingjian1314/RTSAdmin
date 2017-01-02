package com.sjg.sys.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sjg.sys.annotation.FuncDescLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sjg.sys.entity.Log;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.ILogService;
import com.sjg.sys.utils.PrimaryKeyUtil;
import com.sjg.sys.utils.contantUtil;

/**
 * 切面类
 * 
 * @author Hel
 *
 */
@Aspect
@Component
public class SystemLogAspect implements Ordered{

	// 注入Service用于把日志保存数据库
	@Resource
	private ILogService logService;
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	// Controller层切点
	@Pointcut("@annotation(com.sjg.sys.annotation.FuncDescLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知，用户记录controller层日志
	 * 
	 * @param joinPoint
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		User user = (User) session.getAttribute(contantUtil.SESSION_USER);
		// 请求的IP
		String ip = request.getRemoteAddr();
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
		
		// *========数据库日志=========*//
		Log log = new Log();
		try {
			PrimaryKeyUtil pk=new PrimaryKeyUtil(1,2);
			log.setId(String.valueOf(pk.nextId()));
			log.setDescription(getControllerMethodDescription(joinPoint));
			log.setMethod(
					(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setType("0");
			log.setRequestIp(ip);
			log.setExceptionCode(null);
			log.setExceptionDetail(null);
			log.setParams(null);
			if(user != null){
				log.setCreateby(user.getName());
			}else{
				log.setCreateby(null);
			}
			log.setCreateDate(new Date());
			// 保存数据库
			logService.add(log);
//			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		User user = (User) session.getAttribute(contantUtil.SESSION_USER);
		// 获取请求ip
		String ip = request.getRemoteAddr();
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				Object[] o = joinPoint.getArgs();
				System.out.println(o[i]);
				params += o[i] + ";";
			}
		}
		
		String sOut = "";
		//获取异常详情信息并加已筛选
		StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
        	if(s.toString().startsWith("com.sjg.")){
                sOut += "\n\tat " + s + "\r\n";
        	}
        }

        try {
			/* ==========数据库日志========= */
			Log log = new Log();
			PrimaryKeyUtil pk=new PrimaryKeyUtil(1,2);
			log.setId(String.valueOf(pk.nextId()));
			log.setDescription(getControllerMethodDescription(joinPoint));
			log.setExceptionCode(e.getClass().getName());
			log.setType("1");
			log.setExceptionDetail(e.getMessage() + " \n\t " + sOut);
			log.setMethod(
					(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setParams(params);
			if(user != null){
				log.setCreateby(user.getName());
			}else{
				log.setCreateby(null);
			}
			log.setCreateDate(new Date());
			log.setRequestIp(ip);
			// 保存数据库
			logService.add(log);
			System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(FuncDescLog.class).desc();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 执行事物的先后顺序，值越小越靠前
	 */
	@Override
	public int getOrder() {
		return 1;
	}

}
