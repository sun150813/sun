package com.sunjiayuan.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sunjiayuan.mapper.TbGoodMapper;
import com.sunjiayuan.pojo.TbGood;
import com.sunjiayuan.pojo.TbGoodExample;
import com.sunjiayuan.pojo.TbGoodExample.Criteria;
import com.sunjiayuan.service.GoodService;

import com.sunjiayuan.pojo.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class GoodServiceImpl implements GoodService {

	@Autowired
	private TbGoodMapper goodMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbGood> findAll() {
		return goodMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbGood> page=   (Page<TbGood>) goodMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbGood good) {
		goodMapper.insert(good);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbGood good){
		goodMapper.updateByPrimaryKey(good);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbGood findOne(Long id){
		return goodMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			goodMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbGood good, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodExample example=new TbGoodExample();
		Criteria criteria = example.createCriteria();
		
		if(good!=null){			
						if(good.getName()!=null && good.getName().length()>0){
				criteria.andNameLike("%"+good.getName()+"%");
			}
			if(good.getV1()!=null && good.getV1().length()>0){
				criteria.andV1Like("%"+good.getV1()+"%");
			}
			if(good.getV2()!=null && good.getV2().length()>0){
				criteria.andV2Like("%"+good.getV2()+"%");
			}
	
		}
		
		Page<TbGood> page= (Page<TbGood>)goodMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
