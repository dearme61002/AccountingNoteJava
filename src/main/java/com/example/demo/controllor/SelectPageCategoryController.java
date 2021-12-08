package com.example.demo.controllor;

import com.example.demo.mod.Category;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.AccountingNoteSerivce;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SelectPageCategoryController {
    @Autowired
    AccountingNoteSerivce accountingNoteSerivce;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PageCategoryService pageCategoryService;
    @GetMapping(value = "/CategoryListMath")
    public String goCategoryListMath(HttpSession httpSession, Model model,@RequestParam("currPage") int getCurrPage) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");
        List<Category> allCategoryByUserID = categoryService.getAllCategoryByUserID(loginUser.getID());
        double pageSize = 10;
        int currPage =getCurrPage;
        int pageMath = pageCategoryService.getPageMath(allCategoryByUserID.size(), pageSize);
        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<Category> pageCategory = pageCategoryService.getPageCategory(currPage, (int) pageSize, allCategoryByUserID);

        httpSession.setAttribute("currPage",currPage);
        model.addAttribute("pageCategory", pageCategory);
        model.addAttribute("currPage",currPage);
//foreachList

        return "CategoryList";
    }
    @GetMapping(value = "/CategoryListPrevious")
    public String goCategoryListPrevious(HttpSession httpSession, Model model) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");
        List<Category> allCategoryByUserID = categoryService.getAllCategoryByUserID(loginUser.getID());
        double pageSize = 10;

        int pageMath = pageCategoryService.getPageMath(allCategoryByUserID.size(), pageSize);
        int currPage =(int) httpSession.getAttribute("currPage");
        currPage=  currPage<=1?currPage:currPage-1;//next



        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<Category> pageCategory = pageCategoryService.getPageCategory(currPage, (int) pageSize, allCategoryByUserID);


        model.addAttribute("pageCategory", pageCategory);
        model.addAttribute("currPage",currPage);
        httpSession.setAttribute("currPage",currPage);
//foreachList

        return "CategoryList";
    }
    @GetMapping(value = "/CategoryListNext")
    public String goCategoryListNext(HttpSession httpSession, Model model) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");
        List<Category> allCategoryByUserID = categoryService.getAllCategoryByUserID(loginUser.getID());
        double pageSize = 10;
//        int currPage = (int) model.getAttribute("currPage");
        int pageMath = pageCategoryService.getPageMath(allCategoryByUserID.size(), pageSize);
        int currPage =(int) httpSession.getAttribute("currPage");
        currPage=  currPage>=pageMath?currPage:currPage+1;//next



        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<Category> pageCategory = pageCategoryService.getPageCategory(currPage, (int) pageSize, allCategoryByUserID);


        model.addAttribute("pageCategory", pageCategory);
        model.addAttribute("currPage",currPage);
        httpSession.setAttribute("currPage",currPage);
//foreachList

        return "CategoryList";
    }
}
