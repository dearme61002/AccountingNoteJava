package com.example.demo.mapper;

import com.example.demo.mod.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select  UserID,ID,Title,CreateTime,Body,(select count(1) from AccountingNote where AccountingNote.Category = Title and AccountingNote.UserID=#{UserID}) as CountAccountingNote  from Category where UserID=#{UserID}")
    public List<Category> getAllCategoryByUserID(@Param(value = "UserID") String UserID);

    @Insert("INSERT INTO Category (UserID, Title, Body) VALUES (#{UserID},#{Title},#{Body})")
    public int insertOneCategory(@Param(value = "UserID") String UserID, @Param(value = "Title") String Title, @Param(value = "Body") String Body);

    @Select("select COUNT(1) from Category where UserID=#{UserID} and Title=#{Title}")
    public int getCategoryMathByUserIDTitle(@Param(value = "UserID") String UserID, @Param(value = "Title") String Title);

    @Update("UPDATE Category SET Title=#{Title} ,Body=#{Body} WHERE ID =#{ID} ;")
    public int updateOneCategoryByCategoryID(@Param(value = "ID") int ID, @Param(value = "Title") String Title, @Param(value = "Body") String Body);

    @Select("select * from Category where  ID =#{ID}")
    public Category getOneCategoryByCategoryID(@Param(value = "ID") int ID);

    @Select("select COUNT(1) from Category where ID=#{ID} and Title=#{Title}")
    public Integer getCategoryMathByCategoryIDTitle(@Param(value = "ID") String ID, @Param(value = "Title") String Title);

    @Delete("DELETE FROM Category where ID=#{ID} ")
    public int deleteManyCategory(@Param(value = "ID") int ID);

    @Select("select max(ID) from Category")
    public int getMaxOneCategoryID();

    @Delete("DELETE FROM Category where UserID=#{UserID}")
    public Integer deleteManyCategoryByUserID(@Param(value = "UserID") String UserID);
}
