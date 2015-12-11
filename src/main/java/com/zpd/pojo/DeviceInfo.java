/*
 * DeviceInfo.java
 * Copyright(C) 2013-2015 成都PLZT科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-26 Created
 */
package com.zpd.pojo;

import java.io.Serializable;

/**
 * 订单设备详细信息
 * 
 * @author Jacky
 * @version 1.0
 * @date 2015年11月26日
 */
public class DeviceInfo implements Serializable
{

	private String				ssid;
	// 订单设备详细信息ID
	private Integer				id;
	// 订单ID
	private Integer				orderId;
	// 设备分类ID
	private Integer				deviceCategoryId;
	// 设备型号ID
	private Integer				deviceTypeId;
	// 设备唯一标识码
	private String				uniqueCode;
	// 设备序列号
	private String				sno;
	// 设备ESN码
	private String				esn;
	private String				mac;
	private Integer				status;
	// 设备类型,正常安装或者更换设备
	private Integer				type;
	private Integer				openAt;
	private Integer				closeAt;
	// 是否是主要设备
	private Boolean				main;
	private Integer				sceneId;
	private Integer				netState;
	private String				wlanIp;
	// 固件版本
	private String				version;
	private String				apkVersion;
	private String				apkServerVersion;
	private String				monitorSno;
	private Integer				monitorStatus;
	// 大屏、小屏
	private Integer				deviceType;
	// 修改时间
	private Integer				updatedAt;
	// 创建时间
	private Integer				createdAt;
	// 是否可用
	private Boolean				enable;
	private static final long	serialVersionUID	= 1L;

	public String getSsid()
	{
		return ssid;
	}

	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Integer getDeviceCategoryId()
	{
		return deviceCategoryId;
	}

	public void setDeviceCategoryId(Integer deviceCategoryId)
	{
		this.deviceCategoryId = deviceCategoryId;
	}

	public Integer getDeviceTypeId()
	{
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId)
	{
		this.deviceTypeId = deviceTypeId;
	}

	public String getUniqueCode()
	{
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode)
	{
		this.uniqueCode = uniqueCode == null ? null : uniqueCode.trim();
	}

	public String getSno()
	{
		return sno;
	}

	public void setSno(String sno)
	{
		this.sno = sno == null ? null : sno.trim();
	}

	public String getEsn()
	{
		return esn;
	}

	public void setEsn(String esn)
	{
		this.esn = esn == null ? null : esn.trim();
	}

	public String getMac()
	{
		return mac;
	}

	public void setMac(String mac)
	{
		this.mac = mac == null ? null : mac.trim();
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public Integer getOpenAt()
	{
		return openAt;
	}

	public void setOpenAt(Integer openAt)
	{
		this.openAt = openAt;
	}

	public Integer getCloseAt()
	{
		return closeAt;
	}

	public void setCloseAt(Integer closeAt)
	{
		this.closeAt = closeAt;
	}

	public Boolean getMain()
	{
		return main;
	}

	public void setMain(Boolean main)
	{
		this.main = main;
	}

	public Integer getSceneId()
	{
		return sceneId;
	}

	public void setSceneId(Integer sceneId)
	{
		this.sceneId = sceneId;
	}

	public Integer getNetState()
	{
		return netState;
	}

	public void setNetState(Integer netState)
	{
		this.netState = netState;
	}

	public String getWlanIp()
	{
		return wlanIp;
	}

	public void setWlanIp(String wlanIp)
	{
		this.wlanIp = wlanIp == null ? null : wlanIp.trim();
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version == null ? null : version.trim();
	}

	public String getApkVersion()
	{
		return apkVersion;
	}

	public void setApkVersion(String apkVersion)
	{
		this.apkVersion = apkVersion == null ? null : apkVersion.trim();
	}

	public String getApkServerVersion()
	{
		return apkServerVersion;
	}

	public void setApkServerVersion(String apkServerVersion)
	{
		this.apkServerVersion = apkServerVersion == null ? null
				: apkServerVersion.trim();
	}

	public String getMonitorSno()
	{
		return monitorSno;
	}

	public void setMonitorSno(String monitorSno)
	{
		this.monitorSno = monitorSno == null ? null : monitorSno.trim();
	}

	public Integer getMonitorStatus()
	{
		return monitorStatus;
	}

	public void setMonitorStatus(Integer monitorStatus)
	{
		this.monitorStatus = monitorStatus;
	}

	public Integer getDeviceType()
	{
		return deviceType;
	}

	public void setDeviceType(Integer deviceType)
	{
		this.deviceType = deviceType;
	}

	public Integer getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Integer updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public Integer getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt)
	{
		this.createdAt = createdAt;
	}

	public Boolean getEnable()
	{
		return enable;
	}

	public void setEnable(Boolean enable)
	{
		this.enable = enable;
	}

	@Override
	public boolean equals(Object that)
	{
		if (this == that)
		{
			return true;
		}
		if (that == null)
		{
			return false;
		}
		if (getClass() != that.getClass())
		{
			return false;
		}
		DeviceInfo other = (DeviceInfo) that;
		return (this.getId() == null ? other.getId() == null
				: this.getId().equals(other.getId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null
						: this.getOrderId().equals(other.getOrderId()))
				&& (this.getDeviceCategoryId() == null
						? other.getDeviceCategoryId() == null
						: this.getDeviceCategoryId()
								.equals(other.getDeviceCategoryId()))
				&& (this.getDeviceTypeId() == null
						? other.getDeviceTypeId() == null
						: this.getDeviceTypeId()
								.equals(other.getDeviceTypeId()))
				&& (this.getUniqueCode() == null ? other.getUniqueCode() == null
						: this.getUniqueCode().equals(other.getUniqueCode()))
				&& (this.getSno() == null ? other.getSno() == null
						: this.getSno().equals(other.getSno()))
				&& (this.getEsn() == null ? other.getEsn() == null
						: this.getEsn().equals(other.getEsn()))
				&& (this.getMac() == null ? other.getMac() == null
						: this.getMac().equals(other.getMac()))
				&& (this.getStatus() == null ? other.getStatus() == null
						: this.getStatus().equals(other.getStatus()))
				&& (this.getType() == null ? other.getType() == null
						: this.getType().equals(other.getType()))
				&& (this.getOpenAt() == null ? other.getOpenAt() == null
						: this.getOpenAt().equals(other.getOpenAt()))
				&& (this.getCloseAt() == null ? other.getCloseAt() == null
						: this.getCloseAt().equals(other.getCloseAt()))
				&& (this.getMain() == null ? other.getMain() == null
						: this.getMain().equals(other.getMain()))
				&& (this.getSceneId() == null ? other.getSceneId() == null
						: this.getSceneId().equals(other.getSceneId()))
				&& (this.getNetState() == null ? other.getNetState() == null
						: this.getNetState().equals(other.getNetState()))
				&& (this.getWlanIp() == null ? other.getWlanIp() == null
						: this.getWlanIp().equals(other.getWlanIp()))
				&& (this.getVersion() == null ? other.getVersion() == null
						: this.getVersion().equals(other.getVersion()))
				&& (this.getApkVersion() == null ? other.getApkVersion() == null
						: this.getApkVersion().equals(other.getApkVersion()))
				&& (this.getApkServerVersion() == null
						? other.getApkServerVersion() == null
						: this.getApkServerVersion()
								.equals(other.getApkServerVersion()))
				&& (this.getMonitorSno() == null ? other.getMonitorSno() == null
						: this.getMonitorSno().equals(other.getMonitorSno()))
				&& (this.getMonitorStatus() == null
						? other.getMonitorStatus() == null
						: this.getMonitorStatus()
								.equals(other.getMonitorStatus()))
				&& (this.getDeviceType() == null ? other.getDeviceType() == null
						: this.getDeviceType().equals(other.getDeviceType()))
				&& (this.getUpdatedAt() == null ? other.getUpdatedAt() == null
						: this.getUpdatedAt().equals(other.getUpdatedAt()))
				&& (this.getCreatedAt() == null ? other.getCreatedAt() == null
						: this.getCreatedAt().equals(other.getCreatedAt()))
				&& (this.getEnable() == null ? other.getEnable() == null
						: this.getEnable().equals(other.getEnable()));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result + ((getDeviceCategoryId() == null) ? 0
				: getDeviceCategoryId().hashCode());
		result = prime * result + ((getDeviceTypeId() == null) ? 0
				: getDeviceTypeId().hashCode());
		result = prime * result
				+ ((getUniqueCode() == null) ? 0 : getUniqueCode().hashCode());
		result = prime * result
				+ ((getSno() == null) ? 0 : getSno().hashCode());
		result = prime * result
				+ ((getEsn() == null) ? 0 : getEsn().hashCode());
		result = prime * result
				+ ((getMac() == null) ? 0 : getMac().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result
				+ ((getOpenAt() == null) ? 0 : getOpenAt().hashCode());
		result = prime * result
				+ ((getCloseAt() == null) ? 0 : getCloseAt().hashCode());
		result = prime * result
				+ ((getMain() == null) ? 0 : getMain().hashCode());
		result = prime * result
				+ ((getSceneId() == null) ? 0 : getSceneId().hashCode());
		result = prime * result
				+ ((getNetState() == null) ? 0 : getNetState().hashCode());
		result = prime * result
				+ ((getWlanIp() == null) ? 0 : getWlanIp().hashCode());
		result = prime * result
				+ ((getVersion() == null) ? 0 : getVersion().hashCode());
		result = prime * result
				+ ((getApkVersion() == null) ? 0 : getApkVersion().hashCode());
		result = prime * result + ((getApkServerVersion() == null) ? 0
				: getApkServerVersion().hashCode());
		result = prime * result
				+ ((getMonitorSno() == null) ? 0 : getMonitorSno().hashCode());
		result = prime * result + ((getMonitorStatus() == null) ? 0
				: getMonitorStatus().hashCode());
		result = prime * result
				+ ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
		result = prime * result
				+ ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
		result = prime * result
				+ ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
		result = prime * result
				+ ((getEnable() == null) ? 0 : getEnable().hashCode());
		return result;
	}
}