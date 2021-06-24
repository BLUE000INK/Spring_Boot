package com.example.demo.mapper;

import com.example.demo.entity.Praise;

public interface PraiseMapper {
    int deleteByPrimaryKey(Integer praiseId);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(Integer praiseId);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);
}