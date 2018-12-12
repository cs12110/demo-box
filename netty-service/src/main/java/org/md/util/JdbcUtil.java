package org.md.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Jdbc工具类
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午8:33:25
 *
 */
public class JdbcUtil {

	private static Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

	private static ComboPooledDataSource c3p0 = null;
	static {
		try {
			logger.info("Start init data sourece");
			c3p0 = new ComboPooledDataSource("c3p0");
			c3p0.setDriverClass(SysConst.MYSQL_DRIVER);
			c3p0.setJdbcUrl(SysConst.MYSQL_NOTEDB_URL);
			c3p0.setUser(SysConst.MYSQL_USER);
			c3p0.setPassword(SysConst.MYSQL_PASSWORD);

			c3p0.setMaxPoolSize(40);
			c3p0.setMinPoolSize(2);
			c3p0.setInitialPoolSize(10);
			c3p0.setMaxStatements(100);

			Connection conn = c3p0.getConnection();
			conn.close();
			logger.info("Init data source is done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return {@link Connection}
	 */
	public static Connection getDefConnection() {
		try {
			return c3p0.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @param url
	 *            数据库连接url
	 * @param user
	 *            数据库连接用户
	 * @param password
	 *            用户登录密码
	 * @return {@link Connection}
	 */
	// private static Connection getConnection(String url, String user, String
	// password) {
	// Connection conn = null;
	// try {
	// Class.forName(SysConst.MYSQL_DRIVER);
	// conn = DriverManager.getConnection(url, user, password);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return conn;
	// }

	/**
	 * 释放资源
	 * 
	 * @param conn
	 *            连接
	 * @param stm
	 *            声明
	 * @param rs
	 *            结果集
	 */
	public static void fuckoff(Connection conn, Statement stm, ResultSet rs) {
		try {
			if (null != rs && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			// do nothing
		}

		try {
			if (null != stm && !stm.isClosed()) {
				stm.close();
			}
		} catch (Exception e) {
			// do nothing
		}

		try {
			if (null != conn && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}

}
