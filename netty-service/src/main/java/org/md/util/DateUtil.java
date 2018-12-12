package org.md.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午10:00:37
 *
 */
public class DateUtil {

	/**
	 * yyyy-MM-dd hh:mm:ss
	 */
	public static final String SIMPLE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前时间
	 * 
	 * @param dateFormatStr
	 *            时间格式
	 * @return String
	 */
	public static String getCurrentDateStr(String dateFormatStr) {
		String fm = dateFormatStr;
		if (null == dateFormatStr || "".equals(dateFormatStr)) {
			fm = SIMPLE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fm);
		return sdf.format(new Date());
	}

}
