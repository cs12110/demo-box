package demo.demo;

import java.util.regex.Pattern;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.MongoCollection;

import demo.util.Const;
import demo.util.DbUtil;
import demo.util.StdUtil;

/**
 * 
 * @author 3306 TODO 在mysql里面不是有select count(userId) from user的count的功能吗?
 * 
 *         mongodb也有一个count的,只不过有些少的变化
 *
 */
public class CountDemo {
	/**
	 * 测试不带有条件的count
	 */
	@Test
	public void testCount() {
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);
		/*
		 * 统计出user collection里面的所有记录
		 */
		long count = userCollection.count();

		StdUtil.display("user's collection= " + count);
	}

	/**
	 * 测试带有条件的count
	 */
	@Test
	public void testCountByCondition() {
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);
		/*
		 * 比如我想找userId为1的用户
		 */
		Document userIdQuery = new Document();
		userIdQuery.put("userId", "1");

		/*
		 * count里面放置条件
		 */
		long idCount = userCollection.count(userIdQuery);

		StdUtil.display("user's collection and userId=1  count: " + idCount);

		/*
		 * 要是我想查找用户名为Li开头的怎么办?
		 */

		Document userNameQuery = new Document();
		/*
		 * 后面跟着的条件是正则表达式
		 */
		userNameQuery.put("userName", Pattern.compile("^Li_.*$"));

		long nameCount = userCollection.count(userNameQuery);

		StdUtil.display("user's collection and name=Li_*  count: " + nameCount);

	}

}
