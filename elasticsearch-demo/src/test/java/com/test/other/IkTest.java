package com.test.other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;

import com.pkgs.sys.enums.Region;
import com.pkgs.sys.util.ElasticSearchUtil;
import com.pkgs.sys.util.JdbcUtil;

public class IkTest {

	private static String VOD_SQL = "SELECT id,region_id,`name`,director,actor,score,vod_desc FROM bus_vod WHERE show_type = 0 AND score>0";

	private static String TYPE_SQL = "SELECT `name` FROM	bus_type LEFT JOIN map_vod_type ON bus_type.id = map_vod_type.type_id WHERE	map_vod_type.vod_id = ?";

	/**
	 * 测试创建索引
	 */
	@Test
	public void testAddMovieIndex() {
		try {
			TransportClient client = ElasticSearchUtil.getElasticSearchCleint();
			Connection conn = JdbcUtil.getConnection();
			Statement stm = conn.createStatement();
			ResultSet movieResult = stm.executeQuery(VOD_SQL);

			int index = 0;
			int success = 0;
			int failure = 0;

			long count = System.currentTimeMillis();
			long start = System.currentTimeMillis();

			while (movieResult.next()) {
				Map<String, Object> map = buildMap(conn, movieResult);

				if (!saveIntoElasticSearch(client, map)) {
					System.out.println("we fuck off: " + map.get("name"));
					failure++;
				} else {
					success++;
				}

				if (index != 0 && index % 50 == 0) {
					long end = System.currentTimeMillis();
					System.out.println(
							"[" + index + "] spend times: " + (end - start));
					start = System.currentTimeMillis();
				}
			}

			stm.close();
			conn.close();

			ElasticSearchUtil.closeClient(client);
			long allDone = System.currentTimeMillis();
			System.out.println("All done, spend: " + (allDone - count)
					+ ",success: " + success + ",failure: " + failure);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean saveIntoElasticSearch(TransportClient client,
			Map<String, Object> map) {
		RestStatus status = null;
		try {
			IndexRequestBuilder prepareIndex = client.prepareIndex("movie_lib",
					"movies");
			IndexResponse reponse = prepareIndex.setSource(map).get();
			status = reponse.status();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status == RestStatus.CREATED;
	}

	private Map<String, Object> buildMap(Connection conn, ResultSet record) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("name", record.getString("name"));
			map.put("region",
					Region.getRegionNameById(record.getInt("region_id")));
			map.put("score", record.getDouble("score"));
			map.put("director", splitValue(record.getString("director"), " "));
			map.put("actors", splitValue(record.getString("actor"), ","));
			map.put("tag", getTagOfMovie(conn, record.getInt("id")));
			map.put("summary", record.getString("vod_desc"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private String[] getTagOfMovie(Connection conn, Integer movieId) {
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement pstm = conn.prepareStatement(TYPE_SQL);
			pstm.setInt(1, movieId);

			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				list.add(result.getString("name"));
			}

			result.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] tags = new String[list.size()];
		for (int index = 0, size = list.size(); index < size; index++) {
			tags[index] = list.get(index);
		}

		return tags;
	}

	private String[] splitValue(String str, String symbol) {
		if (str == null) {
			return new String[]{};
		}
		return str.split(symbol);
	}

}
