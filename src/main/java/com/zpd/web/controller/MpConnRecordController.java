/*
 * ConnController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpd.json.JsonMessage;
import com.zpd.service.IConnRecordService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Msg;
import com.zpd.utils.SignUtil;

/**
 * 用户连接记录控制器
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
@Controller
@RequestMapping("/mp/cr")
public class MpConnRecordController
{
	private IConnRecordService	connRecordService;

	public void setConnRecordService(IConnRecordService connRecordService)
	{
		this.connRecordService = connRecordService;
	}

	/*
	 * 接收mp方发送的用户连接记录
	 */
	@RequestMapping("/rcv")
	public String receiveCr(ModelMap model, @RequestBody(required = true)
	String data, String sign)
	{
		int code = ErrorCode.FAILED;
		if (SignUtil.checkSignature(sign, data))
		{
			JSONObject jo = JSONObject.parseObject(data);
			if (jo != null)
			{
				String sn = jo.getString("sn");
				if (sn != null)
				{
					JSONArray ja = jo.getJSONArray("wifi_user");
					if (ja != null && !ja.isEmpty())
					{
						code = this.connRecordService.save(sn,ja);
					}
				}
			}
		}
		String jsonData = JsonMessage.getJsonMsg(new Msg(code));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
