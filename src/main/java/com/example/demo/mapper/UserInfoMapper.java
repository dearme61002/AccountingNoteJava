package com.example.demo.mapper;

import com.example.demo.mod.UserInfo;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserInfoMapper {
    @Select("select * from UserInfo")
    public List<UserInfo> getAllUserInfo();

    @Select("select top 1 * from UserInfo where Account=#{Account} and PWD =#{PWD}")
    public UserInfo getOneUser(@Param(value = "Account") String Account, @Param(value = "PWD") String PWD);

    @Select("select top 1 * from UserInfo where ID =#{ID}")
    public UserInfo getOneUserByID(@Param(value = "ID") String ID);

    @Update("UPDATE UserInfo set Account=#{Account},Name=#{Name},Email=#{Email} where ID=#{ID}")
    public int UpateOneUser(@Param(value = "Account") String Account, @Param(value = "Name") String Name, @Param(value = "Email") String Email, @Param(value = "ID") String ID);


    @Options(keyProperty = "onlyID", useGeneratedKeys = true, keyColumn = "onlyID")
    @Insert("insert into  UserInfo (Account,PWD,Name,Email,UserLevel) values(#{Account},#{PWD},#{Name},#{Email},#{UserLevel})")
    public Integer insertOneUser(UserInfo userInfo);

    @Select("select * from UserInfo where onlyID=#{onlyID}")
    public UserInfo getOneUserInfoByOnlyID(Integer onlyID);
@Select("select count(1) from UserInfo where Account=#{Account}")
public Integer getMatnUserInfoByAccount(@Param(value = "Account") String Account);
@Update("UPDATE UserInfo set Name=#{Name},Email=#{Email},Fixdate=#{Fixdate},UserLevel=#{UserLevel} where onlyID=#{onlyID}")
public Integer updateOneUserInfoByOnlyID(@Param(value = "Name") String Name, @Param("Email") String Email, @Param("Fixdate")Date Fixdate,@Param(value = "onlyID") Integer onlyID,@Param(value = "UserLevel")String UserLevel);


@Delete("delete from UserInfo where ID=#{ID}")
    public Integer deleteUsweInfoByID(@Param(value = "ID")String ID);
}
