package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.entity.Student;
import com.util.JSONUtil;
import com.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class TestRedis {
	private static Jedis database = RedisUtil.getJedis();

	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setStuId(12052010);
		stu.setName("cs12110");

		database.set(String.valueOf(stu.getStuId()), JSONUtil.toJSON(stu));

	}

	@Test
	public void testGet() {
		String key = "12052010";
		String jsonOfStu = database.get(key);

		Student stu = JSONUtil.toObj(Student.class, jsonOfStu);

		System.out.println(stu);
	}

	@Test
	public void testRemove() {

		String key = "12052010";
		Long del = database.del(key);

		System.out.println(del);
	}

	@Test
	public void testSave2List() {
		/*
		 * 看起来,list像栈的作用
		 */
		for (int index = 0; index < 5; index++) {
			Student stu = new Student();
			stu.setStuId(index);
			stu.setName("name" + index);

			database.lpush(String.valueOf("stus"), JSONUtil.toJSON(stu));
		}

		Long llen = database.llen("stus");

		System.out.println(llen);
	}

	@Test
	public void testGetFromList() {
		List<String> result = database.lrange("stus", 0, -1);

		for (String each : result) {
			System.out.println(each);
		}

		for (int index = 0; index < 2; index++) {
			String pop = database.lpop("stus");

			System.out.println("remove:" + pop);
		}

		result = database.lrange("stus", 0, -1);

		for (String each : result) {
			System.out.println(each);
		}

	}

	@Test
	public void testSave2Set() {
		/*
		 * 特别注意:这里面存放的key,不能和list里面存放的key重名,不然会出现异常
		 */
		for (int index = 0; index < 5; index++) {
			Student stu = new Student();
			stu.setStuId(index);
			stu.setName("name" + index);

			database.sadd("stuSet", JSONUtil.toJSON(stu));
			database.sadd("stuSet", JSONUtil.toJSON(stu));
		}

		// 去除元素
		database.srem("stuSet", "no");
		Set<String> members = database.smembers("stuSet");

		for (String each : members) {
			System.out.println(each);
		}

	}

	@Test
	public void testSave2Map() {
		Map<String, String> stu = new HashMap<String, String>();

		stu.put("name", "cs12110");
		stu.put("age", "3306");
		stu.put("email", "cs12110@163.com");

		database.hmset("stu", stu);

		if (database.exists("stu")) {

			System.out.println("len: " + database.hlen("stu"));
			System.out.println(database.hvals("stu"));

			Set<String> hkeys = database.hkeys("stu");
			System.out.println(hkeys);

			for (String key : hkeys) {
				System.out.println(database.hmget("stu", key));
			}

			// 删除map里面一个属性
			database.hdel("stu", "name");

			// key后面的属性可以是多个
			List<String> attrs = database.hmget("stu", "name", "email");

			for (String attr : attrs) {
				System.out.println(attr);
			}

		}

	}

	@Test
	public void testClodeDatabase() {
		database.close();
	}

}
