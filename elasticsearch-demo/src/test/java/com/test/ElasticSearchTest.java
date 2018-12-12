package com.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.pkgs.sys.util.ElasticSearchUtil;

/**
 * Elasticsearch简单操作
 *
 * <p>
 * detailed comment
 * 
 * @author 3306 2018年7月6日
 * @see
 * @since 1.0
 */
public class ElasticSearchTest {

	/**
	 * 测试连接
	 */
	@Test
	public void testConnection() {
		TransportClient client = ElasticSearchUtil.getElasticSearchCleint();

		ElasticSearchUtil.closeClient(client);
	}

	/**
	 * 测试创建索引
	 */
	@Test
	public void testAddIndex() {
		try {
			TransportClient client = ElasticSearchUtil.getElasticSearchCleint();

			/*
			 * 索引库名称必须为小写
			 */
			IndexRequestBuilder prepareIndex = client.prepareIndex("my_index",
					"my_tab");
			IndexResponse reponse = prepareIndex
					.setSource(XContentFactory.jsonBuilder().startObject()
							.field("name", "haiyan").field("date", new Date())
							.field("msg", "How are you?").endObject())
					.get();

			System.out.println("Index: " + reponse.getIndex() + "\tType: "
					+ reponse.getType() + "\tId: " + reponse.getId()
					+ "\tStatus: " + reponse.status());

			ElasticSearchUtil.closeClient(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新数据
	 */
	@Test
	public void testUpdate() {
		TransportClient client = ElasticSearchUtil.getElasticSearchCleint();

		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("name", "3306");
		map.put("date", "2008-08-08");
		map.put("msg", "这是何等的窝草呀");

		JSONObject jsonObject = new JSONObject(map);

		UpdateResponse updateResponse = client
				.prepareUpdate("my_index", "my_tab", "iqbdaWQBfbQWQ0JE9slp")
				.setDoc(jsonObject.toString(), XContentType.JSON).get();

		System.out.println("Index:" + updateResponse.getIndex() + "\nType:"
				+ updateResponse.getType() + "\nId:" + updateResponse.getId()
				+ "\nStatus:" + updateResponse.status());
		ElasticSearchUtil.closeClient(client);
	}

	/**
	 * 测试查询
	 */
	@Test
	public void testQuery() {
		TransportClient client = ElasticSearchUtil.getElasticSearchCleint();
		GetResponse getResponse = client
				.prepareGet("my_index", "my_tab", "iabJaWQBfbQWQ0JEPckz").get();

		ElasticSearchUtil.closeClient(client);
		System.out.println(getResponse.getSourceAsString());
	}

	/**
	 * 删除数据
	 */
	@Test
	public void testDelete() {
		try {
			TransportClient client = ElasticSearchUtil.getElasticSearchCleint();
			DeleteResponse response = client
					.delete(new DeleteRequest("my_index", "my_tab",
							"iKbHaWQBfbQWQ0JEh8nL"))
					.get();

			System.out.println("Index:" + response.getIndex() + "\nType:"
					+ response.getType() + "\nId:" + response.getId()
					+ "\nStatus:" + response.status());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
