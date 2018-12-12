package demo.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.client.MongoCollection;

import demo.entity.User;
import demo.util.Const;
import demo.util.DbUtil;

/**
 * 
 * @author 3306 TODO 插入数据
 *
 */
public class InsertDemo {

	/**
	 * 测试往数据库里面插入一条记录
	 * 
	 * 类似sql功能: <br>
	 * insert into<br>
	 * <br>
	 * user(userId,userName,phone)<br>
	 * <br>
	 * values("1","Tomcat","13680543232");
	 */
	@Test
	public void testInsertOne() {
		/*
		 * 这里面的user collection可以自动创建的,不用像mysql那样子
		 */
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		User user = buildUser("1", "Tomcat", "13680543232");

		/*
		 * collection只能使用document对象,所以要将user转换成document
		 */
		Document document = user.toDocument();

		/*
		 * 数据库写入User,然后在mongo_demo数据库自动生成的user collection里面新增了一条用户记录
		 * 
		 */
		userCollection.insertOne(document);

	}

	/**
	 * 当然mongo数据库也提供了一次插入多条数据的操作,那就是insertMany了
	 * 
	 * 而且insertMany的速度比insertOne的快
	 * 
	 * 如果是批量数据进行插入操作的话,推荐使用insertMany
	 */
	@Test
	public void testInsertMany() {

		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * 记住collection只能操作Document对象
		 */
		List<Document> users = new ArrayList<Document>();

		for (int index = 0; index < 5; index++) {
			User user = buildUser("userId" + index, "Li_" + index, "1368054393" + index);

			// 将user转换成document
			users.add(user.toDocument());
		}

		userCollection.insertMany(users);

	}

	/**
	 * 创建一个用户对象
	 * 
	 * @param userId
	 *            用户Id
	 * @param name
	 *            用户名称
	 * @param phone
	 *            手机号码
	 * @return User对象
	 */
	private User buildUser(String userId, String name, String phone) {
		User user = new User();

		user.setUserId(userId);
		user.setUserName(name);
		user.setPhone(phone);

		return user;
	}

}
