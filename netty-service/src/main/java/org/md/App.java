package org.md;

import org.md.service.netty.MdNettyService;
import org.md.util.JdbcUtil;
import org.md.util.SysConst;

/**
 * App
 *
 * <p>
 * 
 * @author huanghuapeng 2018年5月2日下午3:19:34
 *
 */
public class App {

	public static void main(String[] args) {
		initDbConnectionPool();
		initNettyService();
	}

	/**
	 * 初始化数据库连接池
	 */
	private static void initDbConnectionPool() {
		try {
			Class.forName(JdbcUtil.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化Netty 服务
	 */
	private static void initNettyService() {
		try {
			new MdNettyService().startup(SysConst.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
