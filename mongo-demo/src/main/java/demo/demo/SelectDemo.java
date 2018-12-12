package demo.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import demo.util.Const;
import demo.util.DbUtil;
import demo.util.StdUtil;

/**
 * 
 * @author 3306 TODO mongo数据的select的应用
 *
 */
public class SelectDemo {

	/**
	 * 选择出来一条记录
	 * 
	 * select * from user where userId='1';
	 */
	@Test
	public void testSelect1() {

		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * 相当于where userId='1' and userName ='路漫漫'
		 */
		Document userQuery = new Document();
		userQuery.put("userId", "1");
		userQuery.put("userName", Pattern.compile("Tomcat"));

		// 这里面要注意.first(),是指只找出一条数据
		Document userDocument = userCollection.find(userQuery).first();

		/*
		 * 这里面你会留意到有一个_id的属性列,那个是mongodb自动生成的记录Id
		 * 
		 * 删除更新都可以使用ta作为查询条件, 但是传值到前台就需要注意了,这是ObjectId,并不是字符串
		 */
		StdUtil.display(userDocument.toJson());

		// 要是我想找出userId='1' or userName like 'Li_%' 的吗?
		// so check this out :)

		List<Bson> condtions = new ArrayList<Bson>();

		Document userIdQuery = new Document();
		userIdQuery.put("userId", "1");

		Document userNameQuery = new Document();
		userNameQuery.put("userName", Pattern.compile("^Li_.*$"));

		condtions.add(userIdQuery);
		condtions.add(userNameQuery);

		/*
		 * 这里面使用Filters.or
		 */
		FindIterable<Document> find = userCollection.find(Filters.or(condtions));

		StdUtil.display(" ------------ Filters.or -------------");
		for (Document each : find) {
			StdUtil.display(each.toJson());
		}

	}

	/**
	 * 要是我只想要userName和phone的值呢?
	 * 
	 * mongo里面的projection可以使用
	 */
	@Test
	public void testSelect2() {
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		/*
		 * 查找出姓 Li_的
		 */
		Document userIdQuery = new Document();
		userIdQuery.put("userName", Pattern.compile("^Li_.*$"));

		/*
		 * 在上面testSelectOne()里面获取出来有一个_id的东西,但是我不想要这个_id,该怎么设置?
		 * 
		 * fieldName 1为查询出来
		 * 
		 * fieldName 0为不查询出来
		 */
		Document projection = new Document();
		projection.put("userName", 1);
		projection.put("phone", 1);
		projection.put("_id", 0);

		FindIterable<Document> documents = userCollection.find(userIdQuery) // 查询条件
				.projection(projection); // 只获取出来的属性列参数

		/*
		 * 遍历有两种方法:
		 * 
		 * 1.forEach的遍历
		 * 
		 * 2.iterator的遍历
		 */

		// 1.forEach遍历
		StdUtil.display("------------------- forEach ------------------------");
		for (Document each : documents) {
			StdUtil.display(each.toJson());
		}

		// 2.iterator的遍历
		StdUtil.display("------------------- Iterator ------------------------");
		MongoCursor<Document> iterator = documents.iterator();

		while (iterator.hasNext()) {
			Document each = iterator.next(); // 取出每一个元素
			StdUtil.display(each.toJson());
		}

	}

	/**
	 * 测试分页
	 */
	@Test
	public void testPagination() {
		MongoCollection<Document> userCollection = DbUtil.getCollection(Const.USER_COLLECTION);

		// 分页下标
		int pageIndex = 0;

		// 取多少条数据
		int pageSize = 2;

		// 当然find里面可以传条件,find后面可以跟着projection等操作
		FindIterable<Document> documents = userCollection.find().skip(pageIndex * pageSize).limit(pageSize);

		StdUtil.display("----------- pageIndex: ");
		for (Document each : documents) {
			StdUtil.display(each.toJson());
		}

	}

}
