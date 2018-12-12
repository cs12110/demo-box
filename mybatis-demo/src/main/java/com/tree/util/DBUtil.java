package com.tree.util;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * simple introduction
 *
 * <p>
 * detailed comment
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public class DBUtil {

	/**
	 * 配置文件读取流
	 */
	private static InputStream inputStream;

	/**
	 * session工厂
	 */
	private static SqlSessionFactory sessionFactory;

	/**
	 * 初始化
	 */
	static {
		inputStream = Resource.class.getResourceAsStream("/mybatis.xml");
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	/**
	 * 获取mybatis里面的mapper
	 * 
	 * @param clazz
	 *            接口的class对象
	 * @return T
	 */
	public static <T> T getMapperByClazz(Class<? extends T> clazz) {

		if (clazz == null || null == sessionFactory) {
			return null;
		}

		SqlSession session = sessionFactory.openSession();

		return session.getMapper(clazz);

	}

}
