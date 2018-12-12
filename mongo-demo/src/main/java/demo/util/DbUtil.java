package demo.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author 3306 TODO 建立数据库工具类
 *
 */
public class DbUtil {
	/**
	 * 默认测试数据库名称:mongo_demo
	 */
	private static String defaultDatabse = "mongo_demo";
	/**
	 * 数据库地址
	 */
	private static String host = "192.168.0.149";

	/**
	 * 数据库端口号
	 */
	private static int port = 20000;

	private DbUtil() {

	}

	/**
	 * 获取默认数据库(mongo_demo)集合连接(collection相当于mysql的table)
	 * 
	 * @param collectionName
	 *            集合名称
	 * @return MongoCollection
	 */
	public static MongoCollection<Document> getCollection(String collectionName) {

		return getCollection(defaultDatabse, collectionName);
	}

	/**
	 * 获取数据库collection连接
	 * 
	 * @param databaseName
	 *            数据库名称
	 * @param collectionName
	 *            集合名称
	 * @return MongoCollection
	 */
	public static MongoCollection<Document> getCollection(String databaseName, String collectionName) {
		MongoClient client = getClient();

		MongoDatabase database = client.getDatabase(databaseName);

		return database.getCollection(collectionName);
	}

	/**
	 * 获取client,mongo的数据库连接都是顺序 client->database->collection->数据操作
	 * 
	 * @return MongoClient
	 */
	private static MongoClient getClient() {
		MongoClient client = new MongoClient(new ServerAddress(host, port));
		return client;
	}

}
