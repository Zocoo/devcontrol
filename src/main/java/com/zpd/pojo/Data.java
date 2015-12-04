/*
 * Data.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月4日 Created
 */
package com.zpd.pojo;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月4日
 *
 */
public class Data
{
	private String	gw_id;
	private String	transaction_id;
	private String	method;
	private String	type;
	private Integer	code;
	private String	message;
	private Object	object;
	private String	opreation;

	public String getOpreation()
	{
		return opreation;
	}

	public void setOpreation(String opreation)
	{
		this.opreation = opreation;
	}

	public String getGw_id()
	{
		return gw_id;
	}

	public void setGw_id(String gw_id)
	{
		this.gw_id = gw_id;
	}

	public String getTransaction_id()
	{
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id)
	{
		this.transaction_id = transaction_id;
	}

	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object object)
	{
		this.object = object;
	}

}
