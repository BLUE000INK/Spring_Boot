package com.example.demo.controller;

import com.example.demo.entity.Stores;
import com.example.demo.entity.Users;
import com.example.demo.mapper.StoresMapper;
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
@RequestMapping("/stores")
public class StoresController {
    @Autowired
    private StoresMapper storesMapper;
    @PostMapping("/findStore")
    @ResponseBody
    public int findstore(@RequestBody Stores stores){
        if(storesMapper.selectByName(stores.getStoreName()) == null){
            log.info("该店户不存在");
            return 2;
        }else {
            log.info(storesMapper.selectByName(stores.getStoreName()).getStoreName());
            if(storesMapper.selectByName(stores.getStoreName()).getStorePwd().equals(stores.getStorePwd())){
                log.info("登录成功");
                return 1;
            }else{
                log.info("密码错误");
                return 0;
            }
        }
    }

    @PostMapping("/findStoreinfo")
    @ResponseBody
    public Stores findStoreinfo(@RequestBody Stores stores){
        log.info("name:"+stores.getStoreName());
        log.info("ban:"+stores.getStoreBan());
        return storesMapper.selectByName(stores.getStoreName());
    }

    @PostMapping("/insertStore")
    @ResponseBody
    public int insertStore(@RequestBody Stores stores){
        log.info("stores+"+stores.getStoreName());
        stores.setStoreId(storesMapper.selectidMAX() + 1 );
        stores.setStoreBan("1");
        stores.setStoreCardJpg("123");
        stores.setStoreManageJpg("123");
        stores.setStoreLevel(0);
        return storesMapper.insert(stores);
    }

    @RequestMapping("/getAllStore")
    @ResponseBody
    public List<Stores> getAllStore(){
        return storesMapper.getAllStore();
    }

    @RequestMapping("/getAllStoreBan")
    @ResponseBody
    public List<Stores> getAllStoreBan(){
        return storesMapper.getAllStoreBan();
    }

    @PostMapping("/DeletStore")
    @ResponseBody
    public int DeletStore(@RequestBody Stores stores){
        return storesMapper.deleteByPrimaryKey(stores.getStoreId());
    }

    @PostMapping("/BanStore")
    @ResponseBody
    public int BanStore(@RequestBody Stores stores){
        log.info("storeid+"+stores.getStoreId()+"   storeBan+"+stores.getStoreBan());
        return storesMapper.BanStore(stores);
    }
}
