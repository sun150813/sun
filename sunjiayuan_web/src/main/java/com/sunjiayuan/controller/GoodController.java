package com.sunjiayuan.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sunjiayuan.pojo.TbGood;
import com.sunjiayuan.service.GoodService;

import com.sunjiayuan.pojo.PageResult;
import com.sunjiayuan.pojo.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/good")
public class GoodController {

	@Reference
	private GoodService goodService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGood> findAll(){			
		return goodService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return goodService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param good
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbGood good){
		try {
			goodService.add(good);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param good
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbGood good){
		try {
			goodService.update(good);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbGood findOne(Long id){
		return goodService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			goodService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGood good, int page, int rows  ){
		return goodService.findPage(good, page, rows);		
	}
	
}
