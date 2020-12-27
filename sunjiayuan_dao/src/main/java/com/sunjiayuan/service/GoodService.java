package com.sunjiayuan.service;
import java.util.List;
import com.sunjiayuan.pojo.TbGood;

import com.sunjiayuan.pojo.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGood> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbGood good);
	
	
	/**
	 * 修改
	 */
	public void update(TbGood good);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbGood findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbGood good, int pageNum, int pageSize);
	
}
