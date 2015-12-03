/*
 * JsonMessage.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月19日 Created
 */
package com.zpd.utils;

/**
 * JSON消息对象
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月19日
 * 
 */
public class MsgData extends Msg
{

	/** */
	private static final long	serialVersionUID	= -5307554335453989760L;

	public MsgData(int code)
	{
		super(code);
	}

	private Object	data;

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

}
