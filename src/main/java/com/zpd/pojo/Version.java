/*
 * Version.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月7日 Created
 */
package com.zpd.pojo;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月7日
 *
 */
public class Version
{
	private Integer	id;
	private Integer	createdAt;
	private Integer	updatedAt;
	private Integer	type;
	private String	version;
	private boolean	enable;
	private String	url;
	private String	md5;
	private String	vendorName;
	private Integer	vendorId;
	private Integer	deviceTypeId;
	private String	deviceTypeName;
	private String	remark;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
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

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public boolean isEnable()
	{
		return enable;
	}

	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getMd5()
	{
		return md5;
	}

	public void setMd5(String md5)
	{
		this.md5 = md5;
	}

	public String getVendorName()
	{
		return vendorName;
	}

	public void setVendorName(String vendorName)
	{
		this.vendorName = vendorName;
	}

	public Integer getVendorId()
	{
		return vendorId;
	}

	public void setVendorId(Integer vendorId)
	{
		this.vendorId = vendorId;
	}

	public Integer getDeviceTypeId()
	{
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId)
	{
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeName()
	{
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName)
	{
		this.deviceTypeName = deviceTypeName;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

}
