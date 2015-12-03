/*
 * MessageData.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月19日 Created
 */
package com.zpd.utils;

import java.io.Serializable;
import java.util.List;

/**
 * JSON消息数据对象
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月19日
 * 
 */
public class MsgListData implements Serializable
{

	/** */
	private static final long	serialVersionUID	= -2056684448754082036L;
	private int					count;
	private List<?>				rows;
	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<?> getRows()
	{
		return rows;
	}

	public void setRows(List<?> rows)
	{
		this.rows = rows;
	}

}
