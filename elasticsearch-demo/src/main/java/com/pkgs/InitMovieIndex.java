package com.pkgs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pkgs.sys.util.ElasticSearchUtil;

/**
 * 初始化elasticsearch的数据
 *
 * <p>
 * 
 * @author 3306 2018年7月6日
 * @see
 * @since 1.0
 */
public class InitMovieIndex {

	private static final Logger logger = LoggerFactory
			.getLogger(InitMovieIndex.class);

	private static final String INDEX_NAME = "movie_lib";
	private static final String TYPE_NAME = "movies";
	private static final String MOVIE_JSON_PATH = "movie.json";

	public static void main(String[] args) {
		TransportClient client = ElasticSearchUtil.getElasticSearchCleint();

		try {
			InputStream in = InitMovieIndex.class.getClassLoader()
					.getResourceAsStream(MOVIE_JSON_PATH);
			BufferedReader bfr = new BufferedReader(
					new InputStreamReader(in, "utf-8"));

			int success = 0;
			int failure = 0;
			long start = System.currentTimeMillis();

			String line = null;
			while (null != (line = bfr.readLine())) {
				JSONObject json = JSON.parseObject(line);
				IndexRequestBuilder prepareIndex = client
						.prepareIndex(INDEX_NAME, TYPE_NAME);
				IndexResponse reponse = prepareIndex
						.setSource(buildMovieMap(json)).get();
				RestStatus status = reponse.status();
				if (status != RestStatus.CREATED) {
					logger.error("Save {} into elasticsearc doesn't success,{}",
							json.getString("name"), reponse.toString());
					failure++;
				} else {
					success++;
				}
				if (success != 0 && success % 100 == 0) {
					logger.info("Process: " + success);
				}
			}

			logger.info("All done,spend: {}, success: {}, failure: {}",
					(System.currentTimeMillis() - start), success, failure);

			bfr.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ElasticSearchUtil.closeClient(client);
	}

	private static Map<String, Object> buildMovieMap(JSONObject jsonObj) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("name", jsonObj.getString("name"));
		value.put("searchName", jsonObj.getString("name"));
		value.put("region", jsonObj.getString("region"));
		value.put("score", jsonObj.getDouble("score"));
		value.put("director", jsonObj.get("director"));
		value.put("actors", jsonObj.get("actors"));
		value.put("tag", jsonObj.get("tag"));
		value.put("summary", jsonObj.getString("summary"));
		return value;
	}
}
