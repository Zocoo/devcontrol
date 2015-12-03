/***************************************************************************************
 *				  		 JackyYang Personal 版权所有
 *				Copyright (c) 2015 - 2020.Inc All Rights Reserved
 **************************************************************************************/
package com.zpd.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.codec.Hex;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信验证工具类
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年5月12日
 * 
 */
public class SignUtil {
	private final static String SIGN_KEY = "sign";
	private final static String BUSINESS_TYPE_KEY = "business_type";
	/** 私有密钥[maipu] */
	private final static String KEY_MAIPU = "zpd123456";
	/** BUSINESS */
	private final static String BUSINESS_TYPE = "{\"3\":\"gUk7vOJl0rIunPw6Fc6d73mU3U4geJSC\",\"4\":\"FCyDLe2wCXb8pRaPBIe7xXQtppR9ZuY3\"}";

	/**
	 * 获取校验值[maipu]
	 * 
	 * @param jsonData
	 *            json原始数据
	 * @return
	 */
	public static String getSign(String jsonData) {
		MessageDigest md = getMessageDigest("MD5");
		byte[] digest = md.digest(jsonData.concat(KEY_MAIPU).getBytes());
		return new String(Hex.encode(digest));
	}

	/**
	 * JSON数据签证校验[maipu]
	 * 
	 * @param signature
	 *            签证值
	 * @param jsonData
	 *            json原始数据
	 * @return
	 */
	public static boolean checkSignature(String signature, String jsonData) {
		byte[] expected = decode(signature);
		MessageDigest md = getMessageDigest("MD5");
		if (md == null) {
			return false;
		}
		byte[] actual = md.digest(jsonData.concat(KEY_MAIPU).getBytes());
		return matches(expected, actual);
	}

	/**
	 * 获取校验值[inner]
	 * 
	 * @param jsonData
	 *            json原始数据
	 * @return
	 */
	public static String getSign(String jsonData, String businessType) {
		JSONObject jb = JSONObject.parseObject(jsonData);
		Set<String> keySet = jb.keySet();
		List<String> keys = new ArrayList<String>(keySet.size());
		for (String key : keys) {
			keys.add(key);
		}

		MessageDigest md = getMessageDigest("MD5");
		byte[] digest = md.digest(jsonData.concat(KEY_MAIPU).getBytes());
		return new String(Hex.encode(digest));
	}

	/**
	 * JSON数据签证校验[inner][post]
	 * 
	 * @param jsonData
	 *            json原始数据
	 * @return
	 */
	public static boolean checkSignature(String jsonData) {
		String sign = "";
		String businessKey = "";
		JSONObject jb = JSONObject.parseObject(jsonData);
		Set<String> keySet = jb.keySet();
		if (keySet.contains(BUSINESS_TYPE_KEY)) {
			JSONObject busiess = JSONObject.parseObject(BUSINESS_TYPE);
			businessKey = busiess.getString(jb.getString(BUSINESS_TYPE_KEY));
			jb.remove(BUSINESS_TYPE_KEY);
		}
		if (keySet.contains(SIGN_KEY)) {
			sign = jb.getString(SIGN_KEY);
			jb.remove(SIGN_KEY);
		}
		byte[] expected = decode(sign);
		return matches(expected, getByteSign(jb, businessKey));
	}

	/**
	 * JSON数据签证校验[inner][get]
	 * 
	 * @param sortParams
	 * @param signature
	 * @param businessType
	 * @return
	 */
	public static boolean checkSignature(String sortParams, String signature,
			String businessType) {
		byte[] expected = decode(signature);
		JSONObject busiess = JSONObject.parseObject(BUSINESS_TYPE);
		String businessKey = busiess.getString(businessType);
		MessageDigest md = getMessageDigest("MD5");
		if (md == null) {
			return false;
		}
		byte[] actual = md.digest(sortParams.concat(businessKey).getBytes());
		return matches(expected, actual);
	}

	/**
	 * 计算签证值
	 * 
	 * @param jb
	 *            原数据对象
	 * @param keys
	 * @param businessKey
	 * @return
	 */
	private static byte[] getByteSign(JSONObject jb, String businessKey) {
		StringBuilder content = new StringBuilder();
		byte[] byteSign = {};
		Set<String> keySet = jb.keySet();
		List<String> keys = new ArrayList<String>(keySet.size());
		for (String key : keySet) {
			keys.add(key);
		}
		Collections.sort(keys);
		Iterator<String> iterator = keys.iterator();
		if (iterator.hasNext()) {
			String key = iterator.next();
			content.append(key).append("=").append(jb.getString(key));
			iterator.remove();
		}
		while (iterator.hasNext()) {
			content.append("&");
			String key = iterator.next();
			content.append(key).append("=").append(jb.getString(key));
			iterator.remove();
		}
		content.append(businessKey);

		MessageDigest md = getMessageDigest("MD5");
		if (md != null) {
			byteSign = md.digest(content.toString().getBytes());
		}
		return byteSign;
	}

	private static MessageDigest getMessageDigest(String algorithm) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
		}
		return digest;
	}

	private static boolean matches(byte[] expected, byte[] actual) {
		if (expected.length != actual.length) {
			return false;
		}
		int result = 0;
		for (int i = 0; i < expected.length; i++) {
			result |= expected[i] ^ actual[i];
		}
		return result == 0;
	}

	private static byte[] decode(CharSequence signature) {
		return Hex.decode(signature);
	}

	public static void main(String[] args) {
		// String jsonData =
		// "{\"name\":\"hello\",\"source_type\":4,\"app_type\":8,\"sign\":\"43725a663f725aa08bd9c5bc61df1227\",\"business_type\":3}";

		// String jsonData =
		// "{\"audit\": [{\"sn\":\"123456\",\"umac\":\"11:22:33:44:55:66\",\"uip\":\"192.168.1.100\",\"time\":\"2015-11-1912:00:00\",\"url\":\"http://www.qq.com\"},{\"sn\":\"123457\",\"umac\":\"11:22:33:44:55:67\",\"uip\":\"192.168.1.101\",\"time\":\"2015-11-19 12:00:00\",\"url\":\"http://www.aa.com\"}]}";
		// String sign = "4c9224dbe9fa22b99b77be62831a840d";
		String jsonData = "{\"dev_status\":[{\"sn\":\"123456\",\"status\":\"1\",\"vendor\":\"maipu\",\"model\":\"mt1200\",\"fireware\":\"v1.0.1\"}]}";
		String sign = "2bb12d5cb53bdf83244d8151364e0382";
		checkSignature(sign, jsonData);
	}

}
