package com.example.demo;

import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mod.UserInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testgetALLUserInfo {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void contextLoads() {
        List<UserInfo> allUserInfo = userInfoMapper.getAllUserInfo();
        System.out.println(allUserInfo);
        for (UserInfo userInfo:
             allUserInfo) {
            System.out.println(userInfo.getEmail()+"my");
        }
    }
}
