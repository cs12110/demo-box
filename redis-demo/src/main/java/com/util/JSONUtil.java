package com.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 2017年3月25日下午4:08:58
 *
 * @author 3306 TODO JSON辅助类
 *
 */
public class JSONUtil {

	/*
	 * 获取ObjectMapper
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param value
	 *            对象
	 * @return String
	 */
	public static String toJSON(Object value) {

		if (null == value) {
			value = new Object();
		}

		String json = "";
		try {
			json = mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * 将JSON字符串转换成对象
	 * 
	 * @param clazz
	 *            类class对象
	 * @param json
	 *            JSON字符串
	 * @return clazz的对象
	 */
	public static <T> T toObj(Class<? extends T> clazz, String json) {

		if (StrUtil.isEmpty(json)) {
			System.err.println("JSON字符串不能为空");
			return null;
		}

		T value = null;
		try {
			value = (T) mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
