package com.example.demo.controllor;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mod.AccountingNote;
import com.example.demo.mod.Category;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.AccountingNoteSerivce;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PageCategoryService;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SideController {
    @Autowired
    AccountingNoteSerivce accountingNoteSerivce;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PageCategoryService pageCategoryService;
    @Autowired
    UserInfoService userInfoService;

    @GetMapping(value = "/manage")
    public String goManage(Model model,HttpSession httpSession) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");

        //Amount
        Integer sumINAmount = accountingNoteSerivce.getSumINAmount(loginUser.getID());
        Integer sumOutAmount = accountingNoteSerivce.getSumOutAmount(loginUser.getID());

        Integer SumTotalAmount =0;
       if(sumOutAmount==null){
           sumOutAmount=0;

       }
       if(sumINAmount==null){
           sumINAmount =0;
       }
        SumTotalAmount=sumINAmount-sumOutAmount;
        model.addAttribute("SumTotalAmount",SumTotalAmount);
        //Amount

        List<AccountingNote> oneAccountingNoteByUserID = accountingNoteSerivce.getOneAccountingNoteByUserID(loginUser.getID());
        double pageSize = 10;
        int currPage =1;
        int pageMath = pageCategoryService.getPageMath(oneAccountingNoteByUserID.size(), pageSize);
        model.addAttribute("pageSize", pageMath);
        List<AccountingNote> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, oneAccountingNoteByUserID);
        model.addAttribute("pageAccountingNote", pageAccountingNote);
        httpSession.setAttribute("currPage",currPage);//給上下頁使用
        model.addAttribute("currPage",currPage);
        return "manage";
    }

    @GetMapping(value = "/login")
    public String goLogin(Model model) {

        model.addAttribute("UpateOneUser", "");
        return "main";
    }

    @GetMapping(value = "/CategoryAdd")
    public String goCategory() {



        return "Category";
    }

    @GetMapping(value = {"/CategoryList","/CategoryList.html"})
    public String goCategoryList(HttpSession httpSession, Model model) {
        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");
//        檢查是否有預設欄位
        int checkCategoryNull = categoryService.getCategoryMathByUserIDTitle(loginUser.getID(), "未分類");
if(checkCategoryNull<=0){
    categoryService.insertOneCategory(loginUser.getID(), "未分類","未分類");

}

// 檢查是否有預設欄位

        List<Category> allCategoryByUserID = categoryService.getAllCategoryByUserID(loginUser.getID());
        double pageSize = 10;
        int currPage =1;
        int pageMath = pageCategoryService.getPageMath(allCategoryByUserID.size(), pageSize);
        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<Category> pageCategory = pageCategoryService.getPageCategory(currPage, (int) pageSize, allCategoryByUserID);


        model.addAttribute("pageCategory", pageCategory);
        httpSession.setAttribute("currPage",currPage);//給上下頁使用
        model.addAttribute("currPage",currPage);
//foreachList

        return "CategoryList";
    }
    @GetMapping("/NumberList")
    public String goNumberList(Model model,HttpSession httpSession){

        List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();

//刪除登入的資料
        UserInfo userInfo1 = (UserInfo) httpSession.getAttribute("LoginUser");
        UserInfo userInfo = allUserInfo.stream().filter(x -> x.getID().equals(userInfo1.getID())).findFirst().get();
        allUserInfo.remove(userInfo);
        //

        double pageSize = 10;
        int currPage =1;
        int pageMath = pageCategoryService.getPageMath(allUserInfo.size(), pageSize);
        model.addAttribute("pageSize", pageMath);
        List<UserInfo> pageallUserInfo = pageCategoryService.getPageCategory(currPage, (int) pageSize, allUserInfo);
        model.addAttribute("allUserInfo", pageallUserInfo);
        httpSession.setAttribute("currPage",currPage);//給上下頁使用
        model.addAttribute("currPage",currPage);
        //
        return "NumberList";
    }


}
