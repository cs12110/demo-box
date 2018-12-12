package org.md.enums;

/**
 * 执行代码
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午10:49:07
 *
 */
public enum StatusEnum {
	/**
	 * 执行成功
	 */
	SUCCESS(200),

	/**
	 * 执行失败
	 */
	FAILURE(404);

	private final Integer code;

	private StatusEnum(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
