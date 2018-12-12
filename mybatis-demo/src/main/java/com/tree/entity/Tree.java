package com.tree.entity;

import java.util.List;

/**
 * 数据库映射实体类
 * <p>
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public class Tree {
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 种子列表
	 */
	private List<Seed> seedList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Seed> getSeedList() {
		return seedList;
	}

	public void setSeedList(List<Seed> seedList) {
		this.seedList = seedList;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", name=" + name + ", seedList=" + seedList + "]";
	}

}
