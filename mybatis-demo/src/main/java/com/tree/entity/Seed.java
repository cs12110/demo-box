package com.tree.entity;

/**
 * 2017年4月9日上午1:39:13
 *
 * @author 3306 TODO 种子实体类
 *
 */
public class Seed {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 是否存活
	 * 
	 * 1->存活
	 * 
	 * 0->死亡
	 */
	private Integer isAlive;

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

	public Integer getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(Integer isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		return "Seed [id=" + id + ", name=" + name + ", isAlive=" + isAlive + "]";
	}

}
