package org.md.enums;

/**
 * 方法名称
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午10:31:55
 *
 */
public enum MethodEnum {

	/**
	 * 查询
	 */
	QUERY("query"),

	/**
	 * 查询列表
	 */
	QUERY_LIST("queryList"),

	/**
	 * 新增
	 */
	ADD("add"),

	/**
	 * 更新
	 */
	UPDATE("update"),

	/**
	 * 删除
	 */
	DELETE("delete"),

	/**
	 * 错误请求
	 */
	BAD_REQ("bad");

	private MethodEnum(String method) {
		this.method = method;
	}

	private final String method;

	public String getMethod() {
		return method;
	}

}
