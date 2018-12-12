package com.tree.service;

import java.util.List;

import com.tree.entity.Seed;

/**
 * 业务接口
 * <p>
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public interface SeedService {

	/**
	 * 根据id查找seed
	 * 
	 * @param id
	 *            主键
	 * @return {@link Seed}
	 */
	public Seed selectById(Integer id);

	/**
	 * 根据树Id查找种子
	 * 
	 * @param treeId
	 *            id
	 * @return List
	 */
	List<Seed> selectByTreeId(Integer treeId);
}
