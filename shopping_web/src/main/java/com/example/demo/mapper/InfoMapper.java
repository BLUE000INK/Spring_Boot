package com.example.demo.mapper;

import com.example.demo.entity.Info;

import java.util.List;

public interface InfoMapper {
    int deleteByPrimaryKey(Integer infoId);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);
    int selectidMAX();
    List<Info> getAllinfo();
}
