package org.md.service.note;

import org.md.entity.FeedbackBean;

/**
 * 基础业务接口
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午10:55:36
 *
 * @param <T>
 */
public interface BasicService<T> {

	/**
	 * 新增
	 * 
	 * @param bean
	 *            bean
	 * @return boolean
	 */
	public FeedbackBean add(T bean);

	/**
	 * 删除
	 * 
	 * @param bean
	 *            bean
	 * @return boolean
	 */
	public FeedbackBean delete(T bean);

	/**
	 * 更新
	 * 
	 * @param bean
	 *            bean
	 * @return boolean
	 */
	public FeedbackBean update(T bean);

	/**
	 * 主键查询
	 * 
	 * @param id
	 *            主键
	 * @return T
	 */
	public FeedbackBean queryOne(Integer id);

	/**
	 * 查询List
	 * 
	 * @param bean
	 *            查询条件
	 * @return List
	 */
	public FeedbackBean queryList(T bean);
}
