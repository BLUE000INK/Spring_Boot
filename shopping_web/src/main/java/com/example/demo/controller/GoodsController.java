package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Stores;
import com.example.demo.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsMapper goodsMapper;

    @PostMapping("/getAllgoods")
    @ResponseBody
    public List<Goods> getAllgoods(@RequestBody Goods goods){
        return goodsMapper.getAllgoods(goods.getGoodsStorename());
    }

    @PostMapping("/deletgoods")
    @ResponseBody
    public int deletgoods(@RequestBody Goods goods){
        return goodsMapper.deleteByPrimaryKey(goods.getGoodsId());
    }

    @PostMapping("/updategoods")
    @ResponseBody
    public int updategoods(@RequestBody Goods goods){
        return goodsMapper.updateByPrimaryKey(goods);
    }

    @PostMapping("/insertgoods")
    @ResponseBody
    public int insertgoods(@RequestBody Goods goods){
        goods.setGoodsId(goodsMapper.selectidMAX()+1);
        goods.setGoodsJpg("none");
        return goodsMapper.insert(goods);
    }

    @PostMapping("/buygoods")
    @ResponseBody
    public int buygoods(@RequestBody Goods goods){
        log.info("goodsid"+goods.getGoodsId());
        log.info("goodsnumber"+goods.getGoodsNumber());
        return goodsMapper.buegoods(goods);
    }

}
