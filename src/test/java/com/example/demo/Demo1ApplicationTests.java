package com.example.demo;

import com.example.demo.mod.UserInfo;
import com.example.demo.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {
    @Autowired
    UserInfoService userInfoService;
    @Test
    void contextLoads() {

        System.out.println("我是測試");
        UserInfo userInfo=new UserInfo();
        userInfo.setAccount("??dsds");
        userInfo.setEmail("fddf");
        userInfo.setUserLevel("0");
        userInfo.setName("fdff");

        Integer integer = userInfoService.insertOneUser(userInfo);
        System.out.println("cc"+integer);
        System.out.println("gg"+userInfo.getOnlyID());
    }

}
