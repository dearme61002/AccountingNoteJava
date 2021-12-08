package com.example.demo.service;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mod.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageCategoryService<T> {


    public List<T> getPageCategory(int currPage, int pageSize, List<T> AllListdata) {
        //查询全部数据
        //  List<Category> Categorys = categoryMapper.getAllCategoryByUserID(UserID);//使用參數傳入取代
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = currPage * pageSize;
        //   list.stream().skip(strart).limit(end).collect(Collectors.toList());//範例
        return AllListdata.stream().skip(firstIndex).limit(lastIndex).collect(Collectors.toList());//取代下面的程式碼
        //return Categorys.subList(firstIndex, lastIndex); //直接在list中截取 但這個不好 可能會超過長度所以我不使用
    }

    public int getPageMath(double allDatatotal, double intpageSize) {
        int getPageMath = (int) Math.ceil(allDatatotal / intpageSize);
        if(getPageMath==0){
            getPageMath=1;
        }
        return getPageMath;
    }
}
