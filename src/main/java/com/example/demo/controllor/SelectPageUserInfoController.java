package com.example.demo.controllor;

import com.example.demo.mod.AccountingNote;
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
import java.util.stream.Stream;

@Controller
public class SelectPageUserInfoController {
    @Autowired
    AccountingNoteSerivce accountingNoteSerivce;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    PageCategoryService pageCategoryService;
    @GetMapping(value = "/userInfoServiceListMath")
    public String goCategoryListMath(HttpSession httpSession, Model model,@RequestParam("currPage") int getCurrPage) {


        List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();
        ////刪除登入的資料
        UserInfo userInfo1 = (UserInfo) httpSession.getAttribute("LoginUser");
        UserInfo userInfo = allUserInfo.stream().filter(x -> x.getID().equals(userInfo1.getID())).findFirst().get();
        allUserInfo.remove(userInfo1);
        ////刪除登入的資料
        double pageSize = 10;
        int currPage =getCurrPage;
        int pageMath = pageCategoryService.getPageMath(allUserInfo.size(), pageSize);
        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<UserInfo> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, allUserInfo);

        httpSession.setAttribute("currPage",currPage);
        model.addAttribute("allUserInfo", pageAccountingNote);
        model.addAttribute("currPage",currPage);
//foreachList

        return "NumberList";
    }
    @GetMapping(value = "/userInfoListPrevious")
    public String goCategoryListPrevious(HttpSession httpSession, Model model) {

////刪除登入的資料
        List<UserInfo> allUserInfo = userInfoService.getAllUserInfo();
        UserInfo userInfo1 = (UserInfo) httpSession.getAttribute("LoginUser");
        UserInfo userInfo = allUserInfo.stream().filter(x -> x.getID().equals(userInfo1.getID())).findFirst().get();
        allUserInfo.remove(userInfo);
        //
        double pageSize = 10;

        int pageMath = pageCategoryService.getPageMath(allUserInfo.size(), pageSize);
        int currPage =(int) httpSession.getAttribute("currPage");
        currPage=  currPage<=1?currPage:currPage-1;//next



        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<UserInfo> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, allUserInfo);


        model.addAttribute("allUserInfo", pageAccountingNote);
        model.addAttribute("currPage",currPage);
        httpSession.setAttribute("currPage",currPage);
//foreachList

        return "NumberList";
    }
    @GetMapping(value = "/userInfoListNext")
    public String goCategoryListNext(HttpSession httpSession, Model model) {



        List<UserInfo> allUserInfo1 = userInfoService.getAllUserInfo();


//
        //刪除登入的資料
        UserInfo userInfo1 = (UserInfo) httpSession.getAttribute("LoginUser");
        UserInfo userInfo = allUserInfo1.stream().filter(x -> x.getID().equals(userInfo1.getID())).findFirst().get();
        allUserInfo1.remove(userInfo);
//
        double pageSize = 10;

        int pageMath = pageCategoryService.getPageMath(allUserInfo1.size(), pageSize);
        int currPage =(int) httpSession.getAttribute("currPage");
        currPage=  currPage>=pageMath?currPage:currPage+1;//next



        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<UserInfo> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, allUserInfo1);


        model.addAttribute("allUserInfo", pageAccountingNote);
        model.addAttribute("currPage",currPage);
        httpSession.setAttribute("currPage",currPage);
//foreachList

        return "NumberList";
    }
}
