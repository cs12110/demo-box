package com.pkgs.sys.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取Elasticsearch
 *
 * <p>
 * 
 * @author 3306 2018年7月6日
 * @see
 * @since 1.0
 */
public class ElasticSearchUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ElasticSearchUtil.class);

	/**
	 * Elasticsearch 服务器地址
	 */
	public static final String HOST = "10.33.1.111";
	/**
	 * http请求是9200,客户端是9300
	 */
	public static final Integer PORT = 9300;

	/**
	 * 获取elasticsearch客户端
	 * 
	 * @return {@link TransportClient}
	 */
	@SuppressWarnings("resource")
	public static TransportClient getElasticSearchCleint() {
		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new TransportAddress(
							InetAddress.getByName(HOST), PORT));

			logger.info("Connection to elasticsearch->{}:{}", HOST, PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.error("Error connect to->{}:{}", HOST, PORT);
		}
		return client;
	}

	/**
	 * 关闭连接
	 * 
	 * @param client
	 *            客户端
	 */
	public static void closeClient(TransportClient client) {
		logger.info("Close elasticsearch client now");
		if (null != client) {
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
