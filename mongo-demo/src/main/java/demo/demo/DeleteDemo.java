package demo.demo;

import java.util.regex.Pattern;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

import demo.util.Const;
import demo.util.DbUtil;
import demo.util.StdUtil;

/**
 * 
 * @author 3306 TODO 测试mongo数据里面删除数据功能
 *
 */
public class DeleteDemo {
	/**
	 * 测试删除一条数据
	 */
	@Test
	public void testDeleteOne() {
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * 删除的条件 相当于 where userId = '1'
		 * 
		 * 当然你可以userNameQuery.put("userName", Pattern.compile("^Li_.*$"));
		 * 
		 * 根据官方文档,deleteOne只会删除匹配到的第一条记录数据
		 */
		Document deleteQuery = new Document();
		deleteQuery.put("userId", "1");

		/*
		 * 有一个执行返回的结果
		 */
		DeleteResult deleteOne = userCollection.deleteOne(deleteQuery);

		/*
		 * 打印出来删除的记录条数
		 * 
		 * 在这里1位删除成功,0为删除失败
		 */
		StdUtil.display("delete userId=1  result: " + deleteOne.getDeletedCount());

	}

	/**
	 * 测试删除多条数据
	 */
	@Test
	public void testDeleteMany() {
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * 删除的条件 相当于 where userName like Li_%
		 * 
		 * 当然你可以userNameQuery.put("userName", Pattern.compile("^Li_.*$"));
		 * 
		 */
		Document userNameQuery = new Document();
		userNameQuery.put("userName", Pattern.compile("^Li_.*$"));

		/*
		 * 有一个执行返回的结果
		 */
		DeleteResult deleteMany = userCollection.deleteMany(userNameQuery);

		/*
		 * 打印出来删除的记录条数
		 * 
		 */
		StdUtil.display("delete userName=Li_.*  result: " + deleteMany.getDeletedCount());
	}

}
