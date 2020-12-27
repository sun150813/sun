package com.sunjiayuan.mapper;

import com.sunjiayuan.pojo.TbGood;
import com.sunjiayuan.pojo.TbGoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodMapper {
    long countByExample(TbGoodExample example);

    int deleteByExample(TbGoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbGood record);

    int insertSelective(TbGood record);

    List<TbGood> selectByExample(TbGoodExample example);

    TbGood selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbGood record, @Param("example") TbGoodExample example);

    int updateByExample(@Param("record") TbGood record, @Param("example") TbGoodExample example);

    int updateByPrimaryKeySelective(TbGood record);

    int updateByPrimaryKey(TbGood record);
}