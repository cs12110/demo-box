package com.tree.mapper;

import com.tree.entity.Tree;

/**
 * 数据操作接口
 *
 * <p>
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public interface TreeMapper {

	/**
	 * 根据Id获取Tree
	 * 
	 * @param id
	 *            tree的id
	 * @return Tree
	 */
	Tree selectById(Integer id);
}
