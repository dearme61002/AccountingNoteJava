package com.example.demo.service;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mod.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> getAllCategoryByUserID(String UserID){
        List<Category> allCategory = categoryMapper.getAllCategoryByUserID(UserID);
        return allCategory;
    }

    public int insertOneCategory(String UserID,String Title,String Body){
        int i = categoryMapper.insertOneCategory(UserID,Title,Body);
        return i;
    }

    public int getCategoryMathByUserIDTitle(String ID,String Title){
        int i=categoryMapper.getCategoryMathByUserIDTitle(ID,Title);
        return i;
    }
  public Category getOneCategoryByCategoryID(int ID){
      Category oneCategoryByCategoryID = categoryMapper.getOneCategoryByCategoryID(ID);
      return oneCategoryByCategoryID;
  }

  public int updateOneCategoryByCategoryID(int ID,String Title,String Body){
      int i = categoryMapper.updateOneCategoryByCategoryID(ID, Title, Body);
      return i;
  }
  public int deleteManyCategorybyID(int ID){
      int i = categoryMapper.deleteManyCategory(ID);
      return i;
  }

  public int getMaxOneCategoryID(){
      int maxOneCategoryID = categoryMapper.getMaxOneCategoryID();
      return maxOneCategoryID;
  }

public Integer getCategoryMathByCategoryIDTitle(String ID,String Title){
    Integer categoryMathByCategoryIDTitle = categoryMapper.getCategoryMathByCategoryIDTitle(ID, Title);
    return categoryMathByCategoryIDTitle;
}

public  Integer deleteManyCategoryByUserID(String UserID){
    Integer integer = categoryMapper.deleteManyCategoryByUserID(UserID);
    return integer;
}

//    @Select("select COUNT(1) from Category where ID=#{ID} and Title=#{Title}")
//    public Integer getCategoryMathByCategoryIDTitle(@Param(value = "UserID") String UserID, @Param(value = "Title")String Title);

}
