package demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StdUtil {

	/**
	 * 控制台打印信息
	 * 
	 * @param str
	 *            打印信息
	 */
	public static void display(String str) {
		displayTips(getCurrentTimes() + " \t" + str);
	}

	/**
	 * 显示提示
	 * 
	 * @param tips
	 *            提示信息
	 */
	public static void displayTips(String tips) {
		System.err.println("\n\n" + tips);
	}

	/**
	 * 按照2017-01-12 19:04:36格式获取时间
	 * 
	 * @return String
	 */
	private static String getCurrentTimes() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(new Date());
	}

}
