package com.example.demo.controller;

import com.example.demo.entity.Info;
import com.example.demo.entity.Stores;
import com.example.demo.mapper.InfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//log API接口
@Slf4j
@RequestMapping("/info")
public class InfoController{
    @Autowired
    private InfoMapper infoMapper;
    @PostMapping("/addinfo")
    @ResponseBody
    public int addinfo(@RequestBody Info info){
        log.info("购买记录:" + info.getInfoStoreName() + info.getInfoUserName() + info.getInfoGoodNumber() +":id="+info.getInfoGoodId());
        info.setInfoId(infoMapper.selectidMAX()+1);
        return infoMapper.insert(info);
    }

    @RequestMapping("/getAllinfo")
    @ResponseBody
    public List<Info> getAllinfo(){
        return infoMapper.getAllinfo();
    }
}
