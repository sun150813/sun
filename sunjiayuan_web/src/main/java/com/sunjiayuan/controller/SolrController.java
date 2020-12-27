package com.sunjiayuan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunjiayuan.pojo.TbCar;
import com.sunjiayuan.service.CarService;
import entity.HiglightUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther 孙佳圆
 * @date 2020/12/26 10:00
 */
@RestController
@RequestMapping("/solr")
public class SolrController {
    @Autowired
    private SolrTemplate solrTemplate;
    @Reference

    private CarService carService;
    @RequestMapping("/init")
    public String init() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
        List<TbCar> all = carService.findAll();
        solrTemplate.saveBeans(all);
        solrTemplate.commit();;
        return "初始化成功";
    }
    @RequestMapping("/search")
    public List search(@RequestBody TbCar name){
        if(null != name.getName() && !name.getName().equals("")){
            return  HiglightUtil.higlight(1,1000,solrTemplate,name.getName()).getContent();
        }else {
            return carService.findAll();
        }
    }
}
