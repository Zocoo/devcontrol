/*
 * DeviceStatus.java
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
public class DeviceStatus
{
	private String	esn;
	private Integer	sysuptime;
	private Integer	sysmemfree;
	private Integer	sysload;
	private Integer	uptime;
	private Integer	clientcount;
	private Integer	checktime;
	private String	gwmac;
	private String	ssid;
	private String	gwaddress;
	private String	routervendor;
	private String	routertype;
	private String	wanip;
	private String	sv;
	private boolean	online;
	private Integer	quartztime;
	private Integer	pingtime;

	public Integer getQuartztime()
	{
		return quartztime;
	}

	public void setQuartztime(Integer quartztime)
	{
		this.quartztime = quartztime;
	}

	public Integer getPingtime()
	{
		return pingtime;
	}

	public void setPingtime(Integer pingtime)
	{
		this.pingtime = pingtime;
	}

	public Integer getChecktime()
	{
		return checktime;
	}

	public void setChecktime(Integer checktime)
	{
		this.checktime = checktime;
	}

	public String getGwmac()
	{
		return gwmac;
	}

	public void setGwmac(String gwmac)
	{
		this.gwmac = gwmac;
	}

	public String getSsid()
	{
		return ssid;
	}

	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}

	public String getGwaddress()
	{
		return gwaddress;
	}

	public void setGwaddress(String gwaddress)
	{
		this.gwaddress = gwaddress;
	}

	public String getRoutervendor()
	{
		return routervendor;
	}

	public void setRoutervendor(String routervendor)
	{
		this.routervendor = routervendor;
	}

	public String getRoutertype()
	{
		return routertype;
	}

	public void setRoutertype(String routertype)
	{
		this.routertype = routertype;
	}

	public String getWanip()
	{
		return wanip;
	}

	public void setWanip(String wanip)
	{
		this.wanip = wanip;
	}

	public String getSv()
	{
		return sv;
	}

	public void setSv(String sv)
	{
		this.sv = sv;
	}

	public boolean isOnline()
	{
		return online;
	}

	public void setOnline(boolean online)
	{
		this.online = online;
	}

	public String getEsn()
	{
		return esn;
	}

	public void setEsn(String esn)
	{
		this.esn = esn;
	}

	public Integer getSysuptime()
	{
		return sysuptime;
	}

	public void setSysuptime(Integer sysuptime)
	{
		this.sysuptime = sysuptime;
	}

	public Integer getSysmemfree()
	{
		return sysmemfree;
	}

	public void setSysmemfree(Integer sysmemfree)
	{
		this.sysmemfree = sysmemfree;
	}

	public Integer getSysload()
	{
		return sysload;
	}

	public void setSysload(Integer sysload)
	{
		this.sysload = sysload;
	}

	public Integer getUptime()
	{
		return uptime;
	}

	public void setUptime(Integer uptime)
	{
		this.uptime = uptime;
	}

	public Integer getClientcount()
	{
		return clientcount;
	}

	public void setClientcount(Integer clientcount)
	{
		this.clientcount = clientcount;
	}
}
