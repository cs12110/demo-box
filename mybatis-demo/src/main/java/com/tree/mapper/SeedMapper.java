package com.tree.mapper;

import java.util.List;

import com.tree.entity.Seed;

/**
 * 数据操作接口
 *
 * <p>
 * 
 * @author huanghuapeng 2017年4月7日
 * @see
 * @since 1.0
 */
public interface SeedMapper {

	/**
	 * 根据Id获取seed
	 * 
	 * @param id
	 *            seed的id
	 * @return {@link Seed}
	 */
	Seed selectById(Integer id);

	/**
	 * 根据树id查找种子
	 * 
	 * 不能使用@Param("treeId")这个注解,在xml里面${treeId}会出现异常
	 * 
	 * @param treeId
	 *            树id
	 * @return List
	 */
	List<Seed> selectByTreeId(Integer treeId);
}
