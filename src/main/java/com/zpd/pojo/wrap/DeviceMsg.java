/*
 * ConnController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.pojo.wrap;

/**
 * 接送mp发送过来的数据
 * 
 * @author wuyize @version v1.0.0
 * @date 2015年11月20日
 * 
 */
public class DeviceMsg
{
	// 路由器sn码
	private String	esn;
	private Integer	status;
	// 路由器厂商
	private String	vendor;
	// 路由器型号
	private String	model;
	// sv固件版本
	private String	fireware;
	// 路由器ssid
	private String	ssid;
	// 设备mac
	private String	mac;
	// wan口Ip
	private String	wlanip;

	public String getWlanip()
	{
		return wlanip;
	}

	public void setWlanip(String wlanip)
	{
		this.wlanip = wlanip;
	}

	public String getMac()
	{
		return mac;
	}

	public void setMac(String mac)
	{
		this.mac = mac;
	}

	public String getEsn()
	{
		return esn;
	}

	public void setEsn(String esn)
	{
		this.esn = esn;
	}

	public String getSsid()
	{
		return ssid;
	}

	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getVendor()
	{
		return vendor;
	}

	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getFireware()
	{
		return fireware;
	}

	public void setFireware(String fireware)
	{
		this.fireware = fireware;
	}

}
