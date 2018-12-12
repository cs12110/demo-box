package com.dic.eng.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 爬虫辅助类
 *
 * <p>
 * 
 * @author huanghuapeng 2017年12月19日下午4:36:33
 *
 */
public class SpiderUtil {

	/**
	 * 有道词典查询url
	 */
	private static final String YOUDAO_WEBSITE = "http://www.youdao.com/w/eng/";

	/**
	 * 爬取单词页面
	 * 
	 * @param url
	 *            url
	 * @return html字符串
	 */
	private static final int SUCCESS = 200;

	/**
	 * 爬取单词页面
	 * 
	 * @param word
	 *            单词
	 * @return html字符串
	 */
	public static String spider(String word) {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		CloseableHttpClient httpClient = HttpClients.custom().build();
		String url = YOUDAO_WEBSITE + word;
		String html = null;
		int statusCode = -1;
		try {
			HttpPost method = new HttpPost(url);
			// StringEntity entity = new StringEntity("", "utf-8");
			// entity.setContentEncoding("UTF-8");
			// entity.setContentType("application/text");
			// method.setEntity(entity);
			method.setProtocolVersion(HttpVersion.HTTP_1_0);
			method.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			method.setConfig(defaultRequestConfig);
			HttpResponse result = httpClient.execute(method);

			// 请求发送成功，并得到响应
			statusCode = result.getStatusLine().getStatusCode();
			if (statusCode == SUCCESS) {
				html = EntityUtils.toString(result.getEntity());
			}
			httpClient.close();

		} catch (Exception e) {
			html = null;
			//e.printStackTrace();

			System.out.println(url + "  " + statusCode);
			//System.exit(1);
		}
		return html;

	}

}
