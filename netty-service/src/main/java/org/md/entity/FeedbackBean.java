package org.md.entity;

import org.md.util.JsonUtil;

/**
 * 回馈信息bean
 *
 * <p>
 * 
 * @author huanghuapeng 2018年3月30日上午10:46:29
 *
 */
public class FeedbackBean {

	/**
	 * 执行结果编码
	 */
	private Integer code;

	/**
	 * 值
	 */
	private Object value;

	public FeedbackBean() {
	}

	/**
	 * 构建回馈信息
	 * 
	 * @param code
	 *            编码
	 * @param value
	 *            值
	 */
	public FeedbackBean(Integer code) {
		super();
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonStr(this);
	}

}
