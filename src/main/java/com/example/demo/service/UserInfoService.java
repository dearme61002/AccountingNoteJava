package com.example.demo.service;

import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mod.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Value("${PWD}")
    private String PWD;//密碼12345678
    public com.example.demo.mod.UserInfo getOneuserInfo(String Account,String PWD){
        com.example.demo.mod.UserInfo oneUser = userInfoMapper.getOneUser(Account, PWD);
return oneUser;
    }
    public int UpateOneUser(String Account,String Name,String Email,String ID){
        int i = userInfoMapper.UpateOneUser(Account, Name, Email, ID);
        return i;
    }
    public UserInfo getOneuserInfoByID(String ID){
        UserInfo userInfo = userInfoMapper.getOneUserByID(ID);
        return userInfo;
    }

    public List<UserInfo> getAllUserInfo(){
        List<UserInfo> allUserInfo = userInfoMapper.getAllUserInfo();
        return allUserInfo;
    }

    public Integer insertOneUser(UserInfo userInfo){
        userInfo.setPWD(PWD);
        Integer s = userInfoMapper.insertOneUser(userInfo);
        return s;

    }

    public UserInfo getOneUserInfoByOnlyID(Integer onlyID){
        UserInfo oneUserInfoByOnlyID = userInfoMapper.getOneUserInfoByOnlyID(onlyID);
        return oneUserInfoByOnlyID;
    }

    public Integer getMatnUserInfoByAccount(String Account){
        Integer matnUserInfoByAccount = userInfoMapper.getMatnUserInfoByAccount(Account);
        return matnUserInfoByAccount;
    }

    public Integer updateOneUserInfoByOnlyID(String Name,String Email,String UserLevel,Integer OnlyID){
        Date FixDate = new Date();
        Integer integer = userInfoMapper.updateOneUserInfoByOnlyID(Name, Email, FixDate, OnlyID, UserLevel);
        return integer;
    }

    public Integer deleteUsweInfoByID(String ID){
        Integer integer = userInfoMapper.deleteUsweInfoByID(ID);
        return integer;
    }
}
