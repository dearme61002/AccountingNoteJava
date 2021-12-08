package com.example.demo.mapper;

import com.example.demo.mod.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndexDataMapper {


    @Select("select Min(CreateDate)  from AccountingNote")
    public String getMinTime();

@Select("select Max(CreateDate)  from AccountingNote")
    public String getMaxTime();
@Select("select COUNT(1) from AccountingNote")
public String getCountNote();
@Select("select COUNT(1) from UserInfo")
public String getCountuser();

}
