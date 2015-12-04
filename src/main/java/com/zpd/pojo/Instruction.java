/*
 * Instruction.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.pojo;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月3日
 *
 */
public class Instruction
{
	private String	ver;
	private Integer	id;
	private Integer	deviceid;
	private Integer	type;
	private String	url;
	private Integer	num;
	private Integer	updatedat;
	private Integer	createdat;
	private String	esn;
	private boolean	result;
	private boolean	enable;

	public boolean isResult()
	{
		return result;
	}

	public void setResult(boolean result)
	{
		this.result = result;
	}

	public String getVer()
	{
		return ver;
	}

	public void setVer(String ver)
	{
		this.ver = ver;
	}

	public String getEsn()
	{
		return esn;
	}

	public void setEsn(String esn)
	{
		this.esn = esn;
	}

	public Integer getUpdatedat()
	{
		return updatedat;
	}

	public void setUpdatedat(Integer updatedat)
	{
		this.updatedat = updatedat;
	}

	public Integer getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(Integer createdat)
	{
		this.createdat = createdat;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getDeviceid()
	{
		return deviceid;
	}

	public void setDeviceid(Integer deviceid)
	{
		this.deviceid = deviceid;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Integer getNum()
	{
		return num;
	}

	public void setNum(Integer num)
	{
		this.num = num;
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
