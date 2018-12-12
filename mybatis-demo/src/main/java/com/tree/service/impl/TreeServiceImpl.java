package com.tree.service.impl;

import com.tree.entity.Tree;
import com.tree.mapper.TreeMapper;
import com.tree.service.TreeService;
import com.tree.util.DBUtil;

/**
 * 业务接口实现类
 *
 * <p>
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public class TreeServiceImpl implements TreeService {
	/**
	 * 
	 */
	private TreeMapper treeMapper = DBUtil.getMapperByClazz(TreeMapper.class);

	@Override
	public Tree selectById(Integer id) {
		return treeMapper.selectById(id);
	}

}
