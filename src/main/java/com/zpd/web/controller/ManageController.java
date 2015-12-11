/*
 * ManageController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.web.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zpd.json.JsonMessage;
import com.zpd.pojo.Data;
import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.DeviceStatus;
import com.zpd.pojo.Instruction;
import com.zpd.pojo.wrap.DevOnline;
import com.zpd.pojo.wrap.DeviceMsg;
import com.zpd.pojo.wrap.ImageUpgrade;
import com.zpd.pojo.wrap.SsidName;
import com.zpd.service.IDeviceInfoService;
import com.zpd.service.IInstructionService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.MD5Msg;
import com.zpd.utils.RedisClient;
import com.zpd.utils.Time;

/**
 * 用于接收请求
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月3日
 *
 */
@Controller
@RequestMapping("/manage")
public class ManageController implements ErrorCode
{
	private IInstructionService	instructionService;
	private IDeviceInfoService	deviceInfoService;

	public void setDeviceInfoService(IDeviceInfoService deviceInfoService)
	{
		this.deviceInfoService = deviceInfoService;
	}

	public void setInstructionService(IInstructionService instructionService)
	{
		this.instructionService = instructionService;
	}

	/**
	 * 设备注册
	 */
	@RequestMapping("/register")
	public String register(ModelMap model,
			@RequestBody(required = true) String data) throws IOException
	{
		JSONObject json = null;
		DeviceInfo di = null;
		int code = FAILED;
		System.out.println("====>" + data);
		if (data != null)
		{
			json = JSON.parseObject(data);
			if (json != null)
				if (json.getString("gw_id") != null)
				{
					di = this.deviceInfoService
							.getDeviceByEsn(json.getString("gw_id"));
					String name = "heartbeat";
					if (!StringUtils.isEmpty(json.getString("gw_id")))
						name = name + json.getString("gw_id");
					if (di != null)
					{
						DeviceMsg deviceMsg = new DeviceMsg();
						code = SUCCESS;
						DeviceStatus ds = RedisClient.get(name,
								DeviceStatus.class);
						RedisClient.del(name);
						if (ds == null)
							ds = new DeviceStatus();
						JSONObject jb = null;
						jb = json.getJSONObject("valueSet");
						if (jb != null)
						{
							int unixTime = Time.toUnixTime(Time.now());
							ds.setOnline(true);
							ds.setPingtime(unixTime);
							if (!StringUtils.isEmpty(json.getString(("gw_id"))))
							{
								deviceMsg.setEsn(json.getString("gw_id"));
								ds.setEsn(json.getString("gw_id"));
							}
							if (!StringUtils.isEmpty(jb.getString("gw_mac")))
							{
								deviceMsg.setMac(jb.getString("gw_mac"));
								ds.setGwmac(jb.getString("gw_mac"));
							}
							if (!StringUtils.isEmpty(jb.getString("ssid")))
							{
								deviceMsg.setSsid(jb.getString("ssid"));
								ds.setSsid(jb.getString("ssid"));
							}
							if (!StringUtils
									.isEmpty(jb.getString("gw_address")))
								ds.setGwaddress(jb.getString("gw_address"));
							if (!StringUtils
									.isEmpty(jb.getString("router_vendor")))
							{
								deviceMsg.setVendor(
										jb.getString("router_vendor"));
								ds.setRoutervendor(
										jb.getString("router_vendor"));
							}
							if (!StringUtils
									.isEmpty(jb.getString("router_type")))
							{
								deviceMsg.setModel(jb.getString("router_type"));
								ds.setRoutertype(jb.getString("router_type"));
							}
							if (!StringUtils.isEmpty(jb.getString("wan_ip")))
							{
								deviceMsg.setWlanip(jb.getString("wan_ip"));
								ds.setWanip(jb.getString("wan_ip"));
							}
							if (!StringUtils.isEmpty(jb.getString("sv")))
							{
								deviceMsg.setFireware(jb.getString("sv"));
								ds.setSv(jb.getString("sv"));
							}
							if (jb.getInteger("check_time") != null)
								ds.setChecktime(jb.getInteger("check_time"));
							if (ds != null)
								if (!StringUtils.isEmpty(ds.getEsn()))
									RedisClient.set(name, ds);
						}
						code = this.deviceInfoService.updateFromMp(di,
								deviceMsg);
					}
				}
		}
		Data da = new Data();
		da.setCode(code);
		if (code == SUCCESS)
			da.setMessage("success");
		else
			da.setMessage("failed");
		da.setGw_id(json.getString("gw_id"));
		da.setMethod("system");
		da.setOperation("register");
		da.setType("REQUEST");
		String jsonString = JSON.toJSONString(da);
		if (jsonString == null || jsonString.length() == 0)
			jsonString = "出现未知错误，估计是参数错误。";
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(jsonString));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/**
	 * 接收设备的心跳
	 */
	@RequestMapping("/heartbeat")
	public String heartbeat(ModelMap model,
			@RequestBody(required = true) String data) throws IOException
	{
		String jsonString = "";
		Data da = null;
		JSONObject json = null;
		System.out.println("====>" + data);
		int unixTime = Time.toUnixTime(Time.now());
		if (data != null)
		{
			json = JSON.parseObject(data);
			if (json != null)
			{
				JSONObject jo = new JSONObject();
				jo = json.getJSONObject("valueSet");
				if (jo != null)
				{
					String name = "heartbeat";
					if (!StringUtils.isEmpty(json.getString("gw_id")))
					{
						name = name + json.getString("gw_id");
						DeviceInfo di = this.deviceInfoService
								.getDeviceByEsn(json.getString("gw_id"));
						if (di != null)
						{
							DeviceStatus ds = RedisClient.get(name,
									DeviceStatus.class);
							RedisClient.del(name);
							if (ds == null)
								ds = new DeviceStatus();
							ds.setOnline(true);
							ds.setPingtime(unixTime);
							if (jo.getInteger("client_count") != null)
								ds.setClientcount(
										jo.getInteger("client_count"));
							if (!StringUtils.isEmpty(json.getString("gw_id")))
								ds.setEsn(json.getString("gw_id"));
							if (jo.getInteger("sys_load") != null)
								ds.setSysload(jo.getInteger("sys_load"));
							if (jo.getInteger("sys_memfree") != null)
								ds.setSysmemfree(jo.getInteger("sys_memfree"));
							if (jo.getInteger("sys_uptime") != null)
								ds.setSysuptime(jo.getInteger("sys_uptime"));
							if (jo.getInteger("up_time") != null)
								ds.setUptime(jo.getInteger("up_time"));
							if (ds != null)
								RedisClient.set(name, ds);
							if (di.getNetState().equals(2))
							{
								di.setNetState(1);
								this.deviceInfoService.update(di);
							}
						}
					}
				}
				if (!StringUtils.isEmpty(json.getString("gw_id")))
				{
					Instruction ins = this.instructionService
							.queryInsByEsn(json.getString("gw_id"));
					if (ins != null)
						if (ins.getNum() != null)
						{
							ins.setNum(ins.getNum() + 1);
							this.instructionService.update(ins);
						}
					da = new Data();
					da.setGw_id(json.getString("gw_id"));
					da.setType("REQUEST");
					if (ins == null)
					{
						da.setOperation("closeConn");
						da.setCode(0);
						da.setMessage("success");
					} else
					{
						// 0重启，1升级，2修改ssid，3配置设备，4路由器关，5路由器开
						da.setTransaction_id(String.valueOf(ins.getId()));
						if (ins.getType().equals(0))
						{
							da.setOperation("restart");
							da.setMethod("system");
						} else if (ins.getType().equals(1))
						{
							da.setOperation("imageUpgrade");
							da.setMethod("system");
							ImageUpgrade valueSet = new ImageUpgrade();
							if (!StringUtils.isEmpty(ins.getUrl()))
								valueSet.setUrl(ins.getUrl());
							if (!StringUtils.isEmpty(ins.getVer()))
								valueSet.setVer(ins.getVer());
							da.setValueSet(valueSet);
						} else if (ins.getType().equals(2))
						{
							da.setOperation("setWirelessInfo");
							da.setMethod("wireless");
							SsidName ssid = new SsidName();
							if (!StringUtils.isEmpty(ins.getSsid()))
							{
								// ssid.setSsid(
								// "uci set wireless.@wifi-iface[0].ssid="
								// + ins.getSsid());
								ssid.setSsid(ins.getSsid());
							}
							if (ssid != null)
								da.setValueSet(ssid);
						} else if (ins.getType().equals(4)) // 关闭
						{
							da.setOperation("setWirelessInfo");
							da.setMethod("wireless");
							DevOnline disabled = null;
							disabled = new DevOnline();
							disabled.setDisabled(1);
							if (disabled != null)
							{
								da.setValueSet(disabled);
							}
						} else if (ins.getType().equals(5)) // 开启
						{
							da.setOperation("setWirelessInfo");
							da.setMethod("wireless");
							DevOnline disabled = null;
							disabled = new DevOnline();
							disabled.setDisabled(0);
							if (disabled != null)
							{
								da.setValueSet(disabled);
							}
						}
					}
					jsonString = JSON.toJSONString(da);
				}
			}
		}
		if (jsonString == null || jsonString.length() == 0)
			jsonString = "出现未知错误，估计是参数错误。";
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(jsonString));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/**
	 * 接收结果
	 */
	@RequestMapping("/result")
	public String result(ModelMap model,
			@RequestBody(required = true) String data) throws IOException
	{
		String jsonString = "";
		Data da = null;
		JSONObject json = null;
		System.out.println("====>" + data);
		int unixTime = Time.toUnixTime(Time.now());
		if (data != null)
		{
			json = JSON.parseObject(data);
			if (json != null)
			{
				Instruction reins = this.instructionService
						.get(json.getInteger("transaction_id"));
				if (reins != null)
				{
					reins.setEnable(true);
					reins.setUpdatedat(unixTime);
					if (json.getInteger("code") != null)
					{
						if (json.getInteger("code").equals(0))
							reins.setResult(true);
						else
							reins.setResult(false);
						this.instructionService.update(reins);
					}
					if (!StringUtils.isEmpty(json.getString("gw_id")))
					{
						Instruction ins = this.instructionService
								.queryInsByEsn(json.getString("gw_id"));
						if (ins != null)
						{
							if (ins.getNum() != null)
							{
								ins.setNum(ins.getNum() + 1);
								this.instructionService.update(ins);
							}
						}
						da = new Data();
						da.setGw_id(json.getString("gw_id"));
						da.setType("REQUEST");
						if (ins == null)
						{
							da.setOperation("closeConn");
							da.setCode(0);
							da.setMessage("success");
						} else
						{
							da.setTransaction_id(String.valueOf(ins.getId()));
							// 0重启，1升级，2修改ssid，3配置设备，4路由器关，5路由器开
							if (ins.getType().equals(0))
							{
								da.setOperation("restart");
								da.setMethod("system");
							} else if (ins.getType().equals(1))
							{
								da.setOperation("imageUpgrade");
								da.setMethod("system");
								ImageUpgrade valueSet = new ImageUpgrade();
								if (!StringUtils.isEmpty(ins.getUrl()))
									valueSet.setUrl(ins.getUrl());
								if (!StringUtils.isEmpty(ins.getVer()))
									valueSet.setVer(ins.getVer());
								da.setValueSet(valueSet);
							} else if (ins.getType().equals(2))
							{
								da.setOperation("setWirelessInfo");
								da.setMethod("wireless");
								SsidName ssid = new SsidName();
								if (!StringUtils.isEmpty(ins.getSsid()))
								{
									// ssid.setSsid(
									// "uci set wireless.@wifi-iface[0].ssid="
									// + ins.getSsid());
									ssid.setSsid(ins.getSsid());
								}
								if (ssid != null)
								{
									da.setValueSet(ssid);
								}
							} else if (ins.getType().equals(4)) // 关闭
							{
								da.setOperation("setWirelessInfo");
								da.setMethod("wireless");
								DevOnline disabled = null;
								disabled = new DevOnline();
								disabled.setDisabled(1);
								if (disabled != null)
								{
									da.setValueSet(disabled);
								}
							} else if (ins.getType().equals(5)) // 开启
							{
								da.setOperation("setWirelessInfo");
								da.setMethod("wireless");
								DevOnline disabled = null;
								disabled = new DevOnline();
								disabled.setDisabled(0);
								if (disabled != null)
								{
									da.setValueSet(disabled);
								}
							}
						}
						jsonString = JSON.toJSONString(da);
					}
				}
			}
		}
		if (jsonString == null || jsonString.length() == 0)
			jsonString = "出现未知错误，估计是参数错误。";
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(jsonString));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
