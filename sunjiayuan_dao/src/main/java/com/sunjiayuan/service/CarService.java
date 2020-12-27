package com.sunjiayuan.service;
import java.util.List;
import com.sunjiayuan.pojo.TbCar;

import com.sunjiayuan.pojo.PageResult;
import com.sunjiayuan.pojo.TbUser;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CarService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbCar> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbCar car);
	
	
	/**
	 * 修改
	 */
	public void update(TbCar car);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbCar findOne(Long id);
	
	
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
	public PageResult findPage(TbCar car, int pageNum, int pageSize);

    void addCar(List<TbCar> cars, TbUser user);
}
