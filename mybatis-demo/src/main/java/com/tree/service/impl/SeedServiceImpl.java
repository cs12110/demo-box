package com.tree.service.impl;

import java.util.List;

import com.tree.entity.Seed;
import com.tree.mapper.SeedMapper;
import com.tree.service.SeedService;
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
public class SeedServiceImpl implements SeedService {
	/**
	 * 
	 */
	private SeedMapper seedMapper = DBUtil.getMapperByClazz(SeedMapper.class);

	@Override
	public Seed selectById(Integer id) {
		return seedMapper.selectById(id);
	}

	
	@Override
	public List<Seed> selectByTreeId(Integer treeId) {
		return seedMapper.selectByTreeId(treeId);
	}
}
