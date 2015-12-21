/*
 * ImageUpgrade.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月4日 Created
 */
package com.zpd.pojo.wrap;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月4日
 *
 */
public class ImageUpgrade
{
	private String	url;
	private String	ver;
	private String	md5;

	public String getMd5()
	{
		return md5;
	}

	public void setMd5(String md5)
	{
		this.md5 = md5;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getVer()
	{
		return ver;
	}

	public void setVer(String ver)
	{
		this.ver = ver;
	}
}
