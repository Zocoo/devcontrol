/*
 * OrderController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年10月14日 Created
 */
package com.zpd.utils;

import java.io.Serializable;

/**
 * 分页排序参数
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015-10-16
 * 
 */
public class Paginate implements Serializable
{
	/** */
	private static final long	serialVersionUID	= 4800281008750230453L;
	private String				pageSize;
	private String				pageIndex;

	public String getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(String pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getPageIndex()
	{
		return pageIndex;
	}

	public void setPageIndex(String pageIndex)
	{
		this.pageIndex = pageIndex;
	}

}
