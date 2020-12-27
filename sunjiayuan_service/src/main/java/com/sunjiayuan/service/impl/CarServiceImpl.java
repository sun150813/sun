package com.sunjiayuan.service.impl;
import java.util.List;

import com.sunjiayuan.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sunjiayuan.mapper.TbCarMapper;
import com.sunjiayuan.pojo.TbCar;
import com.sunjiayuan.pojo.TbCarExample;
import com.sunjiayuan.pojo.TbCarExample.Criteria;
import com.sunjiayuan.service.CarService;

import com.sunjiayuan.pojo.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private TbCarMapper carMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbCar> findAll() {
		return carMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbCar> page=   (Page<TbCar>) carMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbCar car) {
		carMapper.insert(car);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbCar car){
		carMapper.updateByPrimaryKey(car);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbCar findOne(Long id){
		return carMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			carMapper.deleteByPrimaryKey(id);
		}		
	}


	@Override
	public void addCar(List<TbCar> cars, TbUser user) {
		for (TbCar car : cars) {
			car.setUsername(user.getName());
			car.setUid(user.getId());
			carMapper.insert(car);
		}
	}

	@Override
	public PageResult findPage(TbCar car, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbCarExample example=new TbCarExample();
		Criteria criteria = example.createCriteria();
		
		if(car!=null){			
						if(car.getUsername()!=null && car.getUsername().length()>0){
				criteria.andUsernameLike("%"+car.getUsername()+"%");
			}
			if(car.getName()!=null && car.getName().length()>0){
				criteria.andNameLike("%"+car.getName()+"%");
			}
			if(car.getV1()!=null && car.getV1().length()>0){
				criteria.andV1Like("%"+car.getV1()+"%");
			}
			if(car.getV2()!=null && car.getV2().length()>0){
				criteria.andV2Like("%"+car.getV2()+"%");
			}
			if(car.getV3()!=null && car.getV3().length()>0){
				criteria.andV3Like("%"+car.getV3()+"%");
			}
	
		}
		
		Page<TbCar> page= (Page<TbCar>)carMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
