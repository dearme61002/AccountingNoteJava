package com.example.demo.controllor;

import com.example.demo.mod.AccountingNote;
import com.example.demo.mod.Category;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.AccountingNoteSerivce;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sound.midi.VoiceStatus;
import java.util.List;

@Controller
public class manageController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    AccountingNoteSerivce accountingNoteSerivce;

    @GetMapping("/Manage/Add")
    public String goManageAdd(Model module, HttpSession httpSession) {
        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");
        List<Category> allCategoryByUserID = categoryService.getAllCategoryByUserID(loginUser.getID());
        module.addAttribute("goManageAdd", allCategoryByUserID);//選項資料
        return "manageAdd";
    }





    @PostMapping("/Manage/Insert")
    public String ManageInsert(@RequestParam(value = "UserID") String UserID, @RequestParam(value = "ActType") String ActType, @RequestParam(value = "Category") String Category, @RequestParam(value = "Caption") String Caption, @RequestParam(value = "Amount") int Amount, @RequestParam(value = "Body") String Body,RedirectAttributes redirectAttributes) {
        int i=0;
  try {
       i = accountingNoteSerivce.insertAccountingNote(UserID, Caption, Amount, ActType, Body, Category);
  }catch (Exception e){
      redirectAttributes.addFlashAttribute("insertManage","嚴重錯誤");
      return "redirect:/Manage/Insert";
  }
  if(i>0){
      redirectAttributes.addFlashAttribute("insertManage" ,"加入成功");

  }else {
      redirectAttributes.addFlashAttribute("insertManage","加入失敗");
  }
        Integer oneMaxAccountingNoteID = accountingNoteSerivce.getOneMaxAccountingNoteID();
        return "redirect:/manage/manageAct?AccountingNotesID="+oneMaxAccountingNoteID;


    }
    @GetMapping("/manage/manageAct")
        public String gomanageAct(@RequestParam("AccountingNotesID")String AccountingNotesID,Model model,HttpSession httpSession){
        AccountingNote oneAccountingNoteByID = accountingNoteSerivce.getOneAccountingNoteByID(AccountingNotesID);
        //分類
        UserInfo loginUser = (UserInfo) httpSession.getAttribute("LoginUser");
        List<Category> allCategoryByUserID = categoryService.getAllCategoryByUserID(loginUser.getID());
        model.addAttribute("goManageAdd",allCategoryByUserID);
        //分類
        model.addAttribute("oneAccountingNoteByID",oneAccountingNoteByID);//getID
        //給資料
        AccountingNote oneAccountingNoteByID1 = accountingNoteSerivce.getOneAccountingNoteByID(AccountingNotesID);
        model.addAttribute("oneAccountingNoteByID1",oneAccountingNoteByID1);
        return "manageAct";
    }
    @PostMapping("/manage/Update")
    public  String updateOneAccountingNote(@RequestParam("ID") String ID,@RequestParam("ActType") String ActType,@RequestParam("Category") String Category,@RequestParam("Caption") String Caption,@RequestParam("Amount") Integer Amount,@RequestParam("Body") String Body, RedirectAttributes redirectAttributes){
        Integer integer = accountingNoteSerivce.updateOneAccountingNoteID(Caption, Amount, ActType, Body, Category, ID);
        if(integer>0){
            redirectAttributes.addFlashAttribute("insertManage","更改成功");

        }else{
            redirectAttributes.addFlashAttribute("UpdateCategory","沒有更改喔");
        }

        return "redirect:/manage/manageAct?AccountingNotesID="+ID;
    }



    //
    @PostMapping("/manage/Delete")
    public String deleteOneCategory(@RequestParam(value = "delete",required = false) String[] ID, RedirectAttributes redirectAttributes){




        if(ID==null){

            redirectAttributes.addFlashAttribute("AccountingNoteDelete","沒有選擇刪除的選項，無法刪除");
            return "redirect:/manage";
        }
        try {

            for (String id:
                    ID) {
                //刪除AccountingNote關聯資料
             accountingNoteSerivce.deleteAccountingNoteByID(id);

            }
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("AccountingNoteDelete","嚴重錯誤，無法刪除");

        }


        redirectAttributes.addFlashAttribute("AccountingNoteDelete","刪除成功");



        return "redirect:/manage";
    }


}
