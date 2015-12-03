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
import com.zpd.pojo.ConnRecord;
import com.zpd.pojo.wrap.Conns;
import com.zpd.pojo.wrap.DayConns;
import com.zpd.service.IConnRecordService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.MsgData;
import com.zpd.utils.MsgListData;

/**
 * 用户连接记录控制器
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
@Controller
@RequestMapping("/inner/cr")
public class InnerConnRecordController
{
	private IConnRecordService	connRecordService;

	public void setConnRecordService(IConnRecordService connRecordService)
	{
		this.connRecordService = connRecordService;
	}

	/*
	 * 获取指定设备的当前连接用户（包括用户信息）
	 */
	@RequestMapping("/connUser")
	public String connUser(ModelMap model, @RequestParam(required = true)
	String sn)
	{
		List<ConnRecord> list = null;
		MsgData msgData = new MsgData(ErrorCode.SUCCESS);
		list = connRecordService.getConnUser(sn);
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
		// 去迈普拿最新数据
		// String opStr = "{\"sn\":\"" + sn + "\"}";
		// String sign = SignUtil.getSign(opStr);
		// JSONObject oj = CommonUtil.httpsRequest(Common.DOMAIN + ":"
		// + Common.PORT + "/" + Common.SIGN + "=" + sign, "POST", opStr);
		// if (oj != null)
		// jsonData = oj.toString();
		String jsonData = JsonMessage.getJsonMsg(msgData);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/*
	 * 获取指定设备的当前连接用户(只是数量)
	 */
	@RequestMapping("/conns")
	public String connUserCouns(ModelMap model, @RequestParam(required = true)
	String sn)
	{
		List<ConnRecord> list = null;
		MsgData msgData = new MsgData(ErrorCode.SUCCESS);
		list = connRecordService.getConnUser(sn);
		if (list == null)
		{
			msgData.setData(new Object());
		} else
		{
			Conns cs = new Conns();
			cs.setConns(list.size());
			msgData.setData(cs);
		}
		String jsonData = JsonMessage.getJsonMsg(msgData);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/*
	 * 获取一段时间内每天的连接数量
	 */
	@RequestMapping("/dayCount")
	public String connUserDay(ModelMap model, @RequestParam(required = true)
	String[] sn, int start, int end)
	{
		List<DayConns> list = null;
		MsgData msgData = new MsgData(ErrorCode.SUCCESS);
		list = connRecordService.queryCouns(sn, start, end);
		if (list == null)
		{
			msgData.setData(new Object());
		} else
		{
			msgData.setData(list);
		}
		String jsonData = JsonMessage.getJsonMsg(msgData);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
