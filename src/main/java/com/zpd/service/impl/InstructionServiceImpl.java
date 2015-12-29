/*
 * InstructionServiceImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpd.dao.IDeviceFirmwareVersionLogsDao;
import com.zpd.dao.IDeviceInfoDao;
import com.zpd.dao.IInstructionDao;
import com.zpd.pojo.DeviceFirmwareVersionLogs;
import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.Instruction;
import com.zpd.pojo.Version;
import com.zpd.service.IInstructionService;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Paginate;
import com.zpd.utils.Time;

/**
 * TODO
 * 
 * @author wuyize @version v1.0.0
 * @date 2015年12月3日
 *
 */
public class InstructionServiceImpl implements IInstructionService, ErrorCode
{
	private IInstructionDao					instructionDao;
	private IDeviceFirmwareVersionLogsDao	deviceFirmwareVersionLogsDao;
	private IDeviceInfoDao					deviceInfoDao;

	public void setDeviceInfoDao(IDeviceInfoDao deviceInfoDao)
	{
		this.deviceInfoDao = deviceInfoDao;
	}

	public void setDeviceFirmwareVersionLogsDao(
			IDeviceFirmwareVersionLogsDao deviceFirmwareVersionLogsDao)
	{
		this.deviceFirmwareVersionLogsDao = deviceFirmwareVersionLogsDao;
	}

	public void setInstructionDao(IInstructionDao instructionDao)
	{
		this.instructionDao = instructionDao;
	}

	@Override
	public int save(Instruction t)
	{
		return this.instructionDao.save(t);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Instruction t)
	{
		return this.instructionDao.update(t);
	}

	@Override
	public Instruction get(int id)
	{
		return this.instructionDao.get(id);
	}

	@Override
	public long getCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCount(int key, int value)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Instruction> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instruction> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instruction> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer dealData(String str)
	{
		int c1 = 0;
		int result = -1;
		int unixTime = Time.toUnixTime(Time.now());
		JSONObject json = null;
		if (str != null)
		{
			try
			{
				json = JSON.parseObject(str);
			} catch (Exception e)
			{
				json = null;
			}
			if (json != null)
				if (json.getString("operation") != null)
					if (json.getString("operation").equals("restart"))
					{
						if (json.getJSONArray("sn") != null)
						{
							JSONArray ja = json.getJSONArray("sn");
							if (ja != null)
							{
								for (int i = 0; i < ja.size(); i++)
								{
									Instruction ins = new Instruction();
									ins.setEsn(ja.getString(i));
									ins.setType(0);
									ins.setCreatedat(unixTime);
									ins.setUpdatedat(unixTime);
									ins.setEnable(false);
									ins.setResult(false);
									ins.setNum(0);
									try
									{
										result = this.instructionDao.save(ins);
									} catch (Exception e)
									{
										System.out.println("插入数据错误：" + i
												+ "<===>" + ja.getString(i));
									}
								}
							}
						}
					} else
						if (json.getString("operation").equals("imageUpgrade"))
					{
						if (json.getJSONArray("sn") != null)
						{
							JSONArray ja = json.getJSONArray("sn");
							if (ja != null)
							{
								for (int i = 0; i < ja.size(); i++)
								{
									DeviceFirmwareVersionLogs dfvl = null;
									DeviceInfo di = this.deviceInfoDao
											.getDeviceInfoBySn(ja.getString(i));
									if (di != null)
									{
										dfvl = new DeviceFirmwareVersionLogs();
										dfvl.setCreatedAt(unixTime);
										dfvl.setUpdatedAt(unixTime);
										dfvl.setEnable(true);
										dfvl.setRemark("");
										dfvl.setDeviceId(di.getId());
										dfvl.setStatus(1);
										Instruction ins = new Instruction();
										ins.setEsn(ja.getString(i));
										ins.setType(1);
										Version version = new Version();
										if (json.getInteger("version") != null)
										{
											version = this.instructionDao
													.queryVersion(
															json.getInteger(
																	"version"));
											if (version != null)
											{
												if (!StringUtils.isEmpty(
														version.getUrl()))
												{
													dfvl.setUrl(
															version.getUrl());
													ins.setUrl(
															version.getUrl());
												}
												if (!StringUtils.isEmpty(
														version.getMd5()))
												{
													ins.setMd5(
															version.getMd5());
												}
												if (!StringUtils.isEmpty(
														di.getVersion()))
													dfvl.setVersionPrev(
															di.getVersion());
												if (!StringUtils.isEmpty(
														version.getVersion()))
													ins.setVer(version
															.getVersion());
											}
										}
										ins.setCreatedat(unixTime);
										ins.setUpdatedat(unixTime);
										ins.setEnable(false);
										ins.setResult(false);
										ins.setNum(0);
										if (ins.getUrl() != null
												&& ins.getVer() != null)
										{
											try
											{
												DeviceFirmwareVersionLogs dfvlold = null;
												dfvlold = this.deviceFirmwareVersionLogsDao
														.querydfvlbydid(
																di.getId(), 1);
												if (dfvlold == null)
												{
													dfvlold = this.deviceFirmwareVersionLogsDao
															.querydfvlbydid(
																	di.getId(),
																	2);
													if (dfvlold == null)
													{
														int result1 = this.deviceFirmwareVersionLogsDao
																.save(dfvl);
														if (result1 > 0)
															result = this.instructionDao
																	.save(ins);
													}
												} else
													c1 = 1;
											} catch (Exception e)
											{
												System.out.println("插入数据错误：" + i
														+ "<===>"
														+ ja.getString(i));
											}
										}
									}
								}
							}
						}
					} else if (json.getString("operation").equals("setSsid"))
					{
						if (json.getJSONArray("sn") != null)
						{
							JSONArray ja = json.getJSONArray("sn");
							String ssid = json.getString("ssid");
							if (ja != null && ssid != null)
							{
								for (int i = 0; i < ja.size(); i++)
								{
									Instruction ins = new Instruction();
									ins.setEsn(ja.getString(i));
									ins.setType(2);
									ins.setSsid(ssid);
									ins.setCreatedat(unixTime);
									ins.setUpdatedat(unixTime);
									ins.setEnable(false);
									ins.setResult(false);
									ins.setNum(0);
									try
									{
										result = this.instructionDao.save(ins);
									} catch (Exception e)
									{
										System.out.println("插入数据错误：" + i
												+ "<===>" + ja.getString(i));
									}
								}
							}
						}
					} else if (json.getString("operation").equals("switchSsid"))
					{
						if (json.getJSONArray("sn") != null)
						{
							JSONArray ja = json.getJSONArray("sn");
							if (ja != null)
							{
								for (int i = 0; i < ja.size(); i++)
								{
									Instruction ins = new Instruction();
									ins.setEsn(ja.getString(i));
									boolean c = false;
									if (json.getInteger("disabled") != null)
									{
										if (json.getInteger("disabled")
												.equals(0))
										{
											c = true;
											ins.setType(4);
										} else if (json.getInteger("disabled")
												.equals(1))
										{
											c = true;
											ins.setType(5);
										}
										if (c)
										{
											ins.setCreatedat(unixTime);
											ins.setUpdatedat(unixTime);
											ins.setEnable(false);
											ins.setResult(false);
											ins.setNum(0);
											try
											{
												result = this.instructionDao
														.save(ins);
											} catch (Exception e)
											{
												System.out.println("插入数据错误：" + i
														+ "<===>"
														+ ja.getString(i));
											}
										}
									}
								}
							}
						}
					}
		}
		if (c1 == 0)
			return result > 0 ? SUCCESS : FAILED;
		else
			return UPING;
	}

	@Override
	public Instruction queryInsByEsn(String esn)
	{
		return this.instructionDao.queryInsByEsn(esn);
	}

}
