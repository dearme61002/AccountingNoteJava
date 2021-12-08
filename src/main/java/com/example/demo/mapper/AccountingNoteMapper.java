package com.example.demo.mapper;

import com.example.demo.mod.AccountingNote;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public interface AccountingNoteMapper {
    @Select("select * from AccountingNote")
    public List<com.example.demo.mod.AccountingNote> getAllAccountingNote();

    @Insert("insert  AccountingNote (UserID,Caption,Amount,ActType,Body,Category) values(#{UserID},#{Caption},#{Amount},#{ActType},#{Body},#{Category})")
    public int insertAccountingNote(@Param(value = "UserID") String UserID, @Param(value = "Caption") String Caption, @Param("Amount") int Amount, @Param(value = "ActType") String ActType, @Param(value = "Body") String Body, @Param("Category") String Category);

    @Select("select * from AccountingNote where UserID =#{UserID}")
    public List<AccountingNote> getOneAccountingNoteByUserID(@Param(value = "UserID") String UserID);

    @Update("UPDATE AccountingNote SET Category=#{newCategory}  WHERE UserID=#{UserID}  and Category =#{oldCategory}")
    public Integer updatemany(@Param(value = "UserID") String UserID, @Param(value = "oldCategory") String oldCategory, @Param(value = "newCategory") String newCategory);

    @Select("select sum(Amount) from AccountingNote where UserID=#{UserID} and ActType =#{ActType}")
    public Integer getSumAmount(@Param(value = "UserID") String UserID, @Param(value = "ActType") String ActType);

    @Delete("DELETE FROM AccountingNote where UserID=#{UserID}  and Category=#{Category}")
    public Integer deleteAccountingNote(@Param(value = "UserID") String UserID, @Param(value = "Category") String Category);

    @Select("select * from AccountingNote where ID=#{ID}")
    public AccountingNote getOneAccountingNoteByID(@Param(value = "ID") String ID);

    @Select("select max(ID) from AccountingNote")
    public Integer getOneMaxAccountingNoteID();

    @Update("UPDATE AccountingNote SET Caption=#{Caption},Amount=#{Amount},ActType=#{ActType},Body=#{Body},Category=#{Category} WHERE ID=#{ID}")
    public Integer updateOneAccountingNoteID(@Param(value = "Caption") String Caption, @Param(value = "Amount") Integer Amount, @Param("ActType") String ActType, @Param(value = "Body") String Body, @Param(value = "Category") String Category, @Param(value = "ID") String ID);

    @Delete("DELETE FROM AccountingNote where ID=#{ID}")
    public Integer deleteAccountingNoteByID(@Param(value = "ID") String ID);

    @Delete("DELETE FROM AccountingNote where UserID=#{UserID}")
    public Integer deleteAccountingNoteByUserID(@Param(value = "UserID")String UserID);
}
