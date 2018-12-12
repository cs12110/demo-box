package org.md.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

/**
 * 处理页面请求参数
 *
 * <p>
 * 
 * @author huanghuapeng 2018年5月3日上午9:17:47
 *
 */
public class ReqUtil {

	private FullHttpRequest req;

	private Map<String, String> map = new HashMap<String, String>();

	public ReqUtil(FullHttpRequest req) {
		this.req = req;
	}

	/**
	 * 获取所有请求参数
	 * 
	 * @return 请求参数
	 */
	public Map<String, String> parse() {
		if (map.isEmpty()) {
			parse();
		}
		return this.map;
	}

	/**
	 * 获取值
	 * 
	 * @param key
	 *            参数名称
	 * @return String
	 */
	public String getVal(String key) {
		if (map.isEmpty()) {
			parseToMap();
		}
		return map.get(key);
	}

	/**
	 * 获取页面请求参数
	 * 
	 * @param req
	 *            {@link FullHttpRequest}
	 * @return Map
	 */
	private Map<String, String> parseToMap() {

		/*
		 * Get方式
		 */
		if (req.getMethod() == HttpMethod.GET) {
			QueryStringDecoder decoder = new QueryStringDecoder(req.getUri());
			Map<String, List<String>> parameters = decoder.parameters();
			parameters.forEach((k, v) -> {
				if (v != null && !v.isEmpty()) {
					map.put(k, v.get(0));
				}
			});
		}

		/*
		 * Post方式
		 */
		if (req.getMethod() == HttpMethod.POST) {
			try {
				HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(req);
				decoder.offer(req);
				for (InterfaceHttpData parm : decoder.getBodyHttpDatas()) {
					Attribute data = (Attribute) parm;
					map.put(data.getName(), data.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}

}
