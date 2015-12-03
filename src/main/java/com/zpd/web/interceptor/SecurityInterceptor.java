/*
 * SecurityInterceptor.java
 * Copyright(C) 2013-2015 成都PLZT科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年5月26日 Created
 */
package com.zpd.web.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zpd.utils.SignUtil;

/**
 * 安全访问拦截器【Session过期，非法访问】
 * 
 * @author Jacky
 * @version v1.0
 * @date 2015年5月26日
 * 
 */
public class SecurityInterceptor implements HandlerInterceptor
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception
	{
		boolean mark = false;
		String requestURI = request.getRequestURI();
		String method = request.getMethod();
		// 只处理内部和迈普请求
		if (requestURI.startsWith("/mp/"))
		{
			// 迈普只允许post请求
			mark = ("post".equals(method.toLowerCase()));
		} else if (requestURI.startsWith("/inner"))
		{
			// 内部只允许get请求
			if ("get".equals(method.toLowerCase()))
			{
				String sortKeyValues = StringUtils.EMPTY;
				String sign = request.getParameter("sign");
				String businessType = request.getParameter("business_type");
				if (!StringUtils.isEmpty(sign)
						&& !StringUtils.isEmpty(businessType))
				{
					Map<String, String[]> paramMap = request.getParameterMap();
					TreeMap<String, String[]> treeMap = new TreeMap<String, String[]>(
							paramMap);
					sortKeyValues = sortParams(treeMap);
					System.out.println(sortKeyValues);
					mark = SignUtil.checkSignature(sortKeyValues, sign,
							businessType);
				}
			}
		}
		if (!mark)
		{
			response.setStatus(401);
		}
		return mark;
	}

	private String sortParams(Map<String, String[]> treeMap)
	{
		Set<String> keySet = treeMap.keySet();
		Iterator<String> iterator = keySet.iterator();

		String[] params = new String[keySet.size() - 2];
		int i = 0;
		while (iterator.hasNext())
		{
			String key = iterator.next();
			if ("business_type".equals(key) || "sign".equals(key))
			{
				continue;
			}
			String[] value = treeMap.get(key);
			if (value != null)
			{
				if (value.length == 1)
				{
					params[i] = key + "=" + value[0];
				} else
				{
					String jsonString = JSON.toJSONString(value);
					params[i] = key + "=" + jsonString;
				}
				i++;
			}
		}
		return StringUtils.join(params, "&");
	}
}
