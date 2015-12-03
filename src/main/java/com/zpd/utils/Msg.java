/***************************************************************************************
 *				  		 JackyYang Personal 版权所有
 *				Copyright (c) 2015 - 2020.Inc All Rights Reserved
 **************************************************************************************/
package com.zpd.utils;

import java.io.Serializable;

/**
 * 错误消息 POJO
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年5月18日
 * 
 */
public class Msg implements Serializable
{
	/** */
	private static final long	serialVersionUID	= -5599085870944426108L;
	// 错误码
	private int					code;
	// 错误消息
	private String				msg;

	public Msg(int code)
	{
		this(code, ErrorCode.errorMsg[code]);
	}

	public Msg(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
