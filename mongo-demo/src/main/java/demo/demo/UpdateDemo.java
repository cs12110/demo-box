package demo.demo;

import java.util.regex.Pattern;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import demo.util.Const;
import demo.util.DbUtil;
import demo.util.StdUtil;

/**
 * 
 * @author 3306 TODO 测试mongo数据的update功能
 *
 */
public class UpdateDemo {

	/**
	 * 测试更新一条数据
	 * 
	 * % 重点: 注意update的结构 %
	 */
	@Test
	public void testUpdateOne() {

		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * update有两个参数
		 * 
		 * 1.查询条件
		 * 
		 * 2.更新值
		 */

		// 1.建立查询条件
		Document queryVal = new Document("userId", "1");

		// 2.更新值
		// 将userName设为路漫漫
		Document updateVal = new Document("userName", "路漫漫");

		// 重点来了,下面是更新语句
		// 注意第二个document
		// 当然也有一个返回结构啦
		UpdateResult updateOne = userCollection.updateOne(queryVal, new Document("$set", updateVal));

		StdUtil.display("updateOne userId='1' result: " + updateOne.getModifiedCount());

	}

	/**
	 * 测试更新多条数据
	 * 
	 * % 重点: 注意update的结构 %
	 */
	@Test
	public void testUpdateMany() {

		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * update有两个参数
		 * 
		 * 1.查询条件
		 * 
		 * 2.更新值
		 */

		// 1.建立查询条件
		Document queryVal = new Document("userName", Pattern.compile("^Li_.*$"));

		// 2.更新值
		// 将userName设为路漫漫
		Document updateVal = new Document("userName", "杜浦爱李白");

		// 重点来了,下面是更新语句
		// 注意第二个document
		UpdateResult updateMany = userCollection.updateMany(queryVal, new Document("$set", updateVal));

		StdUtil.display("updateOne userName=Li_.* result: " + updateMany.getModifiedCount());
	}

}
