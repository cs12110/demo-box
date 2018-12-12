package com.pkgs.sys.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 
 * 2017年3月19日上午12:33:25
 *
 * @author 3306 TODO 获取数据库连接
 *         <p>
 *         使用数据库配置文件为: jdbc.properties
 *
 */
public class JdbcUtil {

	private static Connection connection;

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {

		connection = init();
		return connection;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 */
	private static Connection init() {
		Connection connection = null;
		try {

			Properties properties = new Properties();
			InputStream stream = JdbcUtil.class
					.getResourceAsStream("/jdbc.properties");

			properties.load(stream);

			String userName = properties.get("username").toString();
			String password = properties.get("password").toString();
			String driverName = properties.get("driverClassName").toString();
			String url = properties.get("url").toString();

			Class.forName(driverName);

			connection = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @param driverName
	 *            连接驱动名称
	 * @param url
	 *            url地址
	 * @param userName
	 *            用户名称
	 * @param password
	 *            登录密码
	 * @return Connection
	 */
	public static Connection getConnection(String driverName, String url,
			String userName, String password) {
		Connection connection = null;
		try {

			Class.forName(driverName);

			connection = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

}
