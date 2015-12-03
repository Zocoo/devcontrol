/*
 * JsonMessage.java
 * Copyright(C) 2013-2015 成都PLZT科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年5月21日 Created
 */
package com.zpd.json;

import com.zpd.pojo.Message;
import com.zpd.utils.Msg;
import com.zpd.utils.MsgData;

/**
 * JSON消息
 * 
 * @author Jacky @version v1.0
 * @date 2015年5月21日
 * 
 */
public class JsonMessage
{

	/**
	 * 返回指定的JSON消息
	 * 
	 * @param code
	 *            消息编码
	 * @param msg
	 *            消息内容
	 * @return String Json数据
	 */
	public static String getJsonMsg(Msg msg)
	{
		return JsonTools.toJson(msg);
	}

	/**
	 * 返回带数据的的JSON消息
	 * 
	 * @param msgData
	 * @return
	 */
	public static String getJsonMsg(MsgData msgData)
	{
		return JsonTools.toJson(msgData);
	}

	/**
	 * 返回带数据的的JSON消息
	 * 
	 * @param Message
	 * @return
	 */
	public static String getJsonMsg(Message msg)
	{
		return JsonTools.toJson(msg);
	}
}
