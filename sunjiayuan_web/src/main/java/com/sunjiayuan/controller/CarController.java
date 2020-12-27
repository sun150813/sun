package com.sunjiayuan.controller;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sunjiayuan.pojo.*;
import com.sunjiayuan.service.GoodService;
import com.sunjiayuan.service.UserService;
import entity.CookieUtil;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sunjiayuan.service.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/car")
public class CarController {

	@Reference
	private CarService carService;
	@Reference
	private UserService userService;
	@Reference
	private GoodService goodService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbCar> findAll(){			
		return carService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return carService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param car
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbCar car){
		try {
			carService.add(car);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param car
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbCar car){
		try {
			carService.update(car);
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
	public TbCar findOne(Long id){
		return carService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			carService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbCar car, int page, int rows  ){
		return carService.findPage(car, page, rows);		
	}

	@RequestMapping("/addCar")
	public List<TbCar> addCar(Long gid,String name){
		List<TbCar> carList = null;
		TbGood one = goodService.findOne(gid);
		if(name.equals("undefined")){
			boolean a = true;
			String carListString = CookieUtil.getCookieValue( request,"carList","Utf-8");
			if(carListString==null ||carListString.equals("")){
				carListString = "[]";
			}
			carList = JSON.parseArray(carListString,TbCar.class);
			if(carList.size()>0&&carList!=null){
				for (TbCar tbCar : carList) {
					if(tbCar.getGid().equals(gid)){
						tbCar.setName(one.getName());
						tbCar.setNum(tbCar.getNum()+1);
						tbCar.setTotalprice(tbCar.getPrice()*tbCar.getNum());
						a = false;
					}
				}
				if(a){
					TbCar tbCar = new TbCar();
					tbCar.setGid(one.getId());
					tbCar.setName(one.getName());
					tbCar.setNum(1);
					tbCar.setPrice(one.getPrice());
					tbCar.setTotalprice(tbCar.getPrice()*tbCar.getNum());
					carList.add(tbCar);
				}
			}else {

				TbCar tbCar = new TbCar();
				tbCar.setGid(one.getId());
				tbCar.setName(one.getName());
				tbCar.setNum(1);
				tbCar.setPrice(one.getPrice());
				tbCar.setTotalprice(tbCar.getPrice()*tbCar.getNum());
				carList.add(tbCar);

			}
			CookieUtil.setCookie(request,response,"carList",JSON.toJSONString(carList),3600*24,"utf-8");
		}
		else {
			TbUser user = userService.getUser(name);
			carList = (List<TbCar>) redisTemplate.boundHashOps("car").get("carList");
			boolean b= true;
			if(carList.size()<0){
				TbCar tbCar = new TbCar();
				tbCar.setUid(user.getId());
				tbCar.setUsername(user.getName());
				tbCar.setGid(one.getId());
				tbCar.setName(one.getName());
				tbCar.setNum(1);
				tbCar.setPrice(one.getPrice());
				tbCar.setTotalprice(tbCar.getPrice()*tbCar.getNum());
				carList.add(tbCar);
			}else {
				for (TbCar tbCar : carList) {
					if(tbCar.getGid().equals(gid)){
						tbCar.setUid(user.getId());
						tbCar.setUsername(user.getName());
						tbCar.setGid(one.getId());
						tbCar.setName(one.getName());
					tbCar.setNum(tbCar.getNum()+1);
					tbCar.setTotalprice(tbCar.getPrice()*tbCar.getNum());
					b=false;
					}

				}
				if(b){
					TbCar tbCar = new TbCar();
					tbCar.setUid(user.getId());
					tbCar.setUsername(user.getName());
					tbCar.setGid(one.getId());
					tbCar.setName(one.getName());
					tbCar.setNum(1);
					tbCar.setPrice(one.getPrice());
					tbCar.setTotalprice(tbCar.getPrice()*tbCar.getNum());
					carList.add(tbCar);
				}
			}
			redisTemplate.boundHashOps("car").put("carList",carList);
		}
		return carList;

	};
	@RequestMapping("/addRedis")
	public boolean addRedis(){
		String carList = CookieUtil.getCookieValue( request, "carList", "UTF-8");
		if(carList.equals("")||carList==null) {
			carList = "[]";
		}
		List<TbCar> carList1 = JSON.parseArray(carList, TbCar.class);
		if(carList1.size()>0){
			redisTemplate.boundHashOps("car").put("carList",carList1);
		}
		CookieUtil.deleteCookie(request,response,"carList");

		return true;
	}
	@RequestMapping("/addCars")
	public Result addCars(@RequestBody List<TbCar> cars){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		List<TbCar> o = (List<TbCar>) redisTemplate.boundHashOps("car").get("carList");
		for (TbCar tbCar : o) {
			for (TbCar car : cars) {
				if(tbCar.getGid().equals(car.getGid())){
					o.remove(tbCar);
				}
			}
		}
		TbUser user = userService.getUser(name);
		redisTemplate.boundHashOps("car").put("carList",o);
		try {
			carService.addCar(cars,user);
			return new Result(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "添加失败");
		}
	}
}
