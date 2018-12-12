package com.entity;

import java.io.Serializable;

/**
 * 2017年4月6日下午9:33:24
 *
 * @author 3306 TODO 定义学生实体类
 *
 */
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 学生Id
	 */
	private int stuId;

	/**
	 * 学生名称
	 */
	private String name;

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", name=" + name + "]";
	}

}
