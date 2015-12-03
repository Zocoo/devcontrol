/*
 * ConnController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zpd.json.JsonMessage;
import com.zpd.pojo.DeviceInfo;
import com.zpd.service.IDeviceInfoService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.MsgData;
import com.zpd.utils.MsgListData;
import com.zpd.utils.Paginate;

/**
 * 设备信息控制器
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
@Controller
@RequestMapping("/inner/device")
public class InnerDeviceInfoController
{
	private IDeviceInfoService	deviceInfoService;

	public void setDeviceInfoService(IDeviceInfoService deviceInfoService)
	{
		this.deviceInfoService = deviceInfoService;
	}

	/*
	 * 获取版本号
	 */
	@RequestMapping("/ver")
	public String version(ModelMap model, @RequestParam(required = true)
	String[] sn)
	{
		List<DeviceInfo> list = null;
		MsgData msgData = null;
		if (sn.length > 0)
		{
			list = this.deviceInfoService.getDeviceInfoBySn(sn);
			msgData = new MsgData(ErrorCode.SUCCESS);
			if (list == null)
			{
				msgData.setData(new Object());
			} else
			{
				MsgListData data = new MsgListData();
				data.setCount(list.size());
				data.setRows(list);
				msgData.setData(data);
			}
		}
		// String opStr = "{sn:[" + StringUtils.join(sn, ",") + "]}";
		// String sign = SignUtil.getSign(opStr);
		// JSONObject oj = CommonUtil.httpsRequest(Common.DOMAIN + ":" +
		// Common.PORT + "/"
		// + Common.SIGN + "=" + sign, "POST", opStr);
		// if (oj != null)
		// jsonData = oj.toJSONString();
		String jsonData = JsonMessage.getJsonMsg(msgData);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/*
	 * 分页获取设备列表
	 */
	@RequestMapping("/list")
	public String page(ModelMap model, Paginate pag)
	{
		int code = 1;
		List<DeviceInfo> data = null;

		MsgData msgData = new MsgData(code);
		// TODO
		msgData.setData(data);
		String jsonData = JsonMessage.getJsonMsg(msgData);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

}
