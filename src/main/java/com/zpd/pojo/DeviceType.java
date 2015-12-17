/*
 * DeviceType.java
 * Copyright(C) 2013-2015 成都PLZT科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-26 Created
 */
package com.zpd.pojo;

import java.io.Serializable;

/**
 * 每种类别的设备有多种型号
 * 
 * @author Jacky
 * @version 1.0
 * @date 2015年11月26日
 */
public class DeviceType implements Serializable
{

	// 设备型号ID
	private Integer				id;
	// 设备分类ID
	private Integer				deviceCategoryId;
	// 设备型号名称
	private String				name;
	// 设备型号描述
	private String				intro;
	// 创建时间
	private Integer				createdAt;
	// 修改时间
	private Integer				updatedAt;
	// 是否可用
	private Boolean				enable;
	private static final long	serialVersionUID	= 1L;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getDeviceCategoryId()
	{
		return deviceCategoryId;
	}

	public void setDeviceCategoryId(Integer deviceCategoryId)
	{
		this.deviceCategoryId = deviceCategoryId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public String getIntro()
	{
		return intro;
	}

	public void setIntro(String intro)
	{
		this.intro = intro == null ? null : intro.trim();
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

	public Boolean getEnable()
	{
		return enable;
	}

	public void setEnable(Boolean enable)
	{
		this.enable = enable;
	}
}