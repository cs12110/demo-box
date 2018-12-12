package org.md.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午8:18:12
 *
 */
public class JsonUtil {

	/**
	 * 转换对象为JSON字符串
	 * 
	 * @param val
	 *            对象值
	 * @return String
	 */
	public static String toJsonStr(Object val) {
		if (null == val) {
			return "";
		}
		return JSON.toJSONString(val);
	}

	/**
	 * 将json字符串串转换成bean
	 * 
	 * @param beanClass
	 *            bean的class对象
	 * @param jsonStr
	 *            json字符串
	 * @return T
	 */
	public static <T> T toBean(Class<T> beanClass, String jsonStr) {
		JSONObject json = JSON.parseObject(jsonStr);
		return JSON.toJavaObject(json, beanClass);
	}
}
