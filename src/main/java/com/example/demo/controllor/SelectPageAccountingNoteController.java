package com.example.demo.controllor;

import com.example.demo.mod.AccountingNote;
import com.example.demo.mod.Category;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.AccountingNoteSerivce;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PageCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SelectPageAccountingNoteController {
    @Autowired
    AccountingNoteSerivce accountingNoteSerivce;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PageCategoryService pageCategoryService;
    @GetMapping(value = "/AccountingNoteListMath")
    public String goCategoryListMath(HttpSession httpSession, Model model,@RequestParam("currPage") int getCurrPage) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");

        List<AccountingNote> oneAccountingNoteByUserID = accountingNoteSerivce.getOneAccountingNoteByUserID(loginUser.getID());
        double pageSize = 10;
        int currPage =getCurrPage;
        int pageMath = pageCategoryService.getPageMath(oneAccountingNoteByUserID.size(), pageSize);
        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<AccountingNote> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, oneAccountingNoteByUserID);

        httpSession.setAttribute("currPage",currPage);
        model.addAttribute("pageAccountingNote", pageAccountingNote);
        model.addAttribute("currPage",currPage);
//foreachList

        return "manage";
    }
    @GetMapping(value = "/AccountingNoteListPrevious")
    public String goCategoryListPrevious(HttpSession httpSession, Model model) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");

        List<AccountingNote> oneAccountingNoteByUserID = accountingNoteSerivce.getOneAccountingNoteByUserID(loginUser.getID());
        double pageSize = 10;

        int pageMath = pageCategoryService.getPageMath(oneAccountingNoteByUserID.size(), pageSize);
        int currPage =(int) httpSession.getAttribute("currPage");
        currPage=  currPage<=1?currPage:currPage-1;//next



        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<AccountingNote> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, oneAccountingNoteByUserID);


        model.addAttribute("pageAccountingNote", pageAccountingNote);
        model.addAttribute("currPage",currPage);
        httpSession.setAttribute("currPage",currPage);
//foreachList

        return "manage";
    }
    @GetMapping(value = "/AccountingNoteListNext")
    public String goCategoryListNext(HttpSession httpSession, Model model) {

        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");

        List<AccountingNote> oneAccountingNoteByUserID = accountingNoteSerivce.getOneAccountingNoteByUserID(loginUser.getID());
        double pageSize = 10;

        int pageMath = pageCategoryService.getPageMath(oneAccountingNoteByUserID.size(), pageSize);
        int currPage =(int) httpSession.getAttribute("currPage");
        currPage=  currPage>=pageMath?currPage:currPage+1;//next



        model.addAttribute("pageSize", pageMath);
        //foreachList

        List<AccountingNote> pageAccountingNote = pageCategoryService.getPageCategory(currPage, (int) pageSize, oneAccountingNoteByUserID);


        model.addAttribute("pageAccountingNote", pageAccountingNote);
        model.addAttribute("currPage",currPage);
        httpSession.setAttribute("currPage",currPage);
//foreachList

        return "manage";
    }
}
