/***************************************************************************************
 *				  		 JackyYang Personal 版权所有
 *				Copyright (c) 2015 - 2020.Inc All Rights Reserved
 **************************************************************************************/
package com.zpd.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 通用工具类
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年5月13日
 * 
 */
public class CommonUtil
{
	private static Logger	log	= LogFactory.getLogger(CommonUtil.class);

	/**
	 * HTTP Request
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param method
	 *            请求方式
	 * @param opStr
	 *            请求参数
	 * @return JSONObject
	 */
	public static JSONObject httpsRequest(String requestUrl, String method,
			String opStr)
	{
		JSONObject jsonObject = null;
		try
		{
			// TrustManager[] tm = { new SSLTrustManager() };
			// SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			// sslContext.init(null, tm, new SecureRandom());
			// SSLSocketFactory sf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			// conn.setSSLSocketFactory(sf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			conn.setRequestMethod(method);

			if (null != opStr)
			{
				OutputStream os = conn.getOutputStream();
				os.write(opStr.getBytes("UTF-8"));
				os.close();
			}

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null)
			{
				sb.append(str);
			}

			br.close();
			isr.close();
			is.close();
			is = null;
			conn.disconnect();
			jsonObject = JSONObject.parseObject(sb.toString());

		} catch (MalformedURLException e)
		{
			log.error(e.getMessage());
		} catch (IOException e)
		{
			log.error(e.getMessage());
		}
		return jsonObject;
	}

}
