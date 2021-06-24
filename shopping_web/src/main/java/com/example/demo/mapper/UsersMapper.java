package com.example.demo.mapper;

import com.example.demo.entity.Stores;
import com.example.demo.entity.Users;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByName(String name);
    int userCount();
    int selectidMAX();
    int myupdate(Users record);
    List<Users> getAllUser();
    int UserCredit21(Users record);
    int UserCredit22(Users record);
}
