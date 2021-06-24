package com.example.demo.mapper;

import com.example.demo.entity.Stores;

import java.util.List;

public interface StoresMapper {
    int deleteByPrimaryKey(Integer storeId);

    int insert(Stores record);

    int insertSelective(Stores record);

    Stores selectByPrimaryKey(Integer storeId);

    int updateByPrimaryKeySelective(Stores record);

    int updateByPrimaryKey(Stores record);



    Stores selectByName(String name);
    int storeCount();
    int selectidMAX();
    List<Stores> getAllStore();
    List<Stores> getAllStoreBan();
    int BanStore(Stores record);
}
