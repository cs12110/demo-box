package com.tree.service;

import com.tree.entity.Tree;

/**
 * 业务接口
 * <p>
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public interface TreeService {

	/**
	 * 根据id查找tree
	 * 
	 * @param id
	 *            主键
	 * @return Tree
	 */
	public Tree selectById(Integer id);
}
