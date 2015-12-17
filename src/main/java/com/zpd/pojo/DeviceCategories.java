/*
 * DeviceCategories.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月17日 Created
 */
package com.zpd.pojo;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月17日
 *
 */
public class DeviceCategories
{
	private Integer	id;
	private String	name;
	private String	intro;
	private Integer	createdAt;
	private Integer	updatedAt;
	private boolean	enable;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIntro()
	{
		return intro;
	}

	public void setIntro(String intro)
	{
		this.intro = intro;
	}

	public Integer getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt)
	{
		this.createdAt = createdAt;
	}

	public Integer getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Integer updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public boolean isEnable()
	{
		return enable;
	}

	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
}
