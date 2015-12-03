/***************************************************************************************
 *				  		 Jackyyang Personal 版权所有
 *				Copyright (c) 2014 -***.Inc All Rights Reserved
 **************************************************************************************/
package com.zpd.json;

import com.alibaba.fastjson.JSONObject;

/**
 * 将对象转换为Json数据的工具类
 * 
 * @author Jacky
 * @version v1.0
 * @date 2015年5月21日
 * 
 */
public class JsonTools
{
	/**
	 * 转换对象为默认的JSON数据
	 * 
	 * @param o
	 * @return
	 */
	public static String toJson(Object o)
	{
		return JSONObject.toJSONString(o);
	}
	
}
