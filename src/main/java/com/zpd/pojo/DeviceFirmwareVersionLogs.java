/*
 * DeviceFirmwareVersionLogs.java
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
public class DeviceFirmwareVersionLogs
{
	private Integer	id;
	private Integer	createdAt;
	private Integer	updatedAt;
	private boolean	enable;
	private String	version;
	private String	versionPrev;
	private Integer	status;
	private String	remark;
	private String	url;
	private Integer	deviceId;

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

	public boolean isEnable()
	{
		return enable;
	}

	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getVersionPrev()
	{
		return versionPrev;
	}

	public void setVersionPrev(String versionPrev)
	{
		this.versionPrev = versionPrev;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Integer getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(Integer deviceId)
	{
		this.deviceId = deviceId;
	}

}
