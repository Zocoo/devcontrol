/*
 * ConnController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpd.json.JsonMessage;
import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.wrap.DeviceMsg;
import com.zpd.service.IDeviceInfoService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Msg;
import com.zpd.utils.SignUtil;

/**
 * 设备信息控制器
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
@Controller
@RequestMapping("/device")
public class DeviceInfoController
{
	private IDeviceInfoService	deviceInfoService;

	public void setDeviceInfoService(IDeviceInfoService deviceInfoService)
	{
		this.deviceInfoService = deviceInfoService;
	}

	/*
	 * 接收mp方发送的最新设备信息
	 */
	@RequestMapping("/rcv")
	public String receiveDi(ModelMap model, @RequestBody(required = true)
	String data, String sign)
	{
		int code = ErrorCode.FAILED;
		if (SignUtil.checkSignature(sign, data))
		{
			JSONObject jo = JSONObject.parseObject(data);
			if (jo != null)
			{
				String sdeviceMsg = jo.getString("dev_status");
				List<DeviceMsg> list = JSONArray.parseArray(sdeviceMsg,
						DeviceMsg.class);
				for (DeviceMsg deviceMsg : list)
				{
					if (!StringUtils.isEmpty(deviceMsg.getSn()))
					{
						DeviceInfo olddeviceInfo = this.deviceInfoService
								.getDeviceInfoBySn(deviceMsg.getSn());
						if (olddeviceInfo != null)
							code = this.deviceInfoService.updateFromMp(
									olddeviceInfo, deviceMsg);
					}
				}
			}
		}
		String jsonData = JsonMessage.getJsonMsg(new Msg(code));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
