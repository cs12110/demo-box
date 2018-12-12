package org.md.entity;

import java.io.Serializable;

import org.md.util.JsonUtil;

/**
 * 笔记类
 *
 * <p>
 * 
 * @author huanghuapeng 2018年3月30日上午11:01:34
 *
 */
public class NoteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 题目
	 */
	private String title;

	/**
	 * 笔记
	 */
	private String note;

	/**
	 * 创建用户
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String uptTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUptTime() {
		return uptTime;
	}

	public void setUptTime(String uptTime) {
		this.uptTime = uptTime;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonStr(this);
	}

}
