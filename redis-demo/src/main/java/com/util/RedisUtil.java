package com.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 2017年4月6日下午9:36:20
 *
 * @author 3306 TODO Redis数据库工具类
 *
 */
/**
 * 2017年4月6日下午10:52:23
 *
 * @author 3306 TODO
 *
 */
public class RedisUtil {

	private static JedisPool pool;

	/*
	 * 密码
	 */
	private static String auth = "root";

	/*
	 * 控制一个pool最多有多少个状态位idle(空闲)的jedis实例
	 * 
	 * 默认为8
	 */
	private static int maxIdle = 200;

	/*
	 * 等待连接的最大时间,单位毫秒
	 * 
	 * 默认为-1,表示用不超时
	 */
	private static int maxWait = 10000;

	private static int timeout = 1000;

	/*
	 * 在borrow一个jedis实例时,是否提前今夕validate操作
	 * 
	 * 如果为true则得到的实例都是可用
	 */
	private static boolean testOnBorrow = true;

	/**
	 * 初始化
	 */
	static {

		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWait);
		config.setTestOnBorrow(testOnBorrow);

		pool = new JedisPool(config, Const.REDIS_SERVER, Const.PORT, timeout, auth);

	}

	/**
	 * 获取数据库连接
	 * 
	 * @return {@link Jedis}
	 */
	public static Jedis getJedis() {

		if (null != pool) {
			return pool.getResource();
		}

		return null;
	}

	/**
	 * 归还jedis
	 * 
	 * @param jedis
	 */
	public static void returnResource(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
			jedis = null;
		}
	}

}