package com.example.demo.controllor;

import com.example.demo.mod.AccountingNote;
import com.example.demo.mod.Category;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.AccountingNoteSerivce;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
@Autowired
    AccountingNoteSerivce accountingNoteSerivce;
@Autowired
CategoryService categoryService;

    @GetMapping("/UserInfo/goAdd")
    public String goUserInfoAdd() {
        return "NumberAdd";
    }
@PostMapping("/User/Add")
    public String UserInfoAdd(@RequestParam(value = "Account")String Account,@RequestParam(value = "Name")String Name,@RequestParam(value = "Email")String Email,@RequestParam(value = "UserLevel")String UserLevel, RedirectAttributes redirectAttributes){
    Integer integer;
    UserInfo userInfo=new UserInfo();
    //檢查使用者重複
    Integer matnUserInfoByAccount = userInfoService.getMatnUserInfoByAccount(Account);
    if (matnUserInfoByAccount>0){
        redirectAttributes.addFlashAttribute("UserMsg","帳號重複囉!!!");
        return "redirect:/UserInfo/goAdd";
    }


    //

        try {

            userInfo.setAccount(Account);
            userInfo.setName(Name);
            userInfo.setEmail(Email);
            userInfo.setUserLevel(UserLevel);

            integer = userInfoService.insertOneUser(userInfo);
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("UserMsg","嚴重錯誤");
            return "redirect:/UserInfo/goAdd";
        }



    if(integer>0){
        redirectAttributes.addFlashAttribute("UserMsg","加入成功");
    }else {
        redirectAttributes.addFlashAttribute("UserMsg","加入失敗");
    }
return "redirect:/User/Act?UserInfoOnlyID="+userInfo.getOnlyID();
}

@GetMapping("/User/Act")
    public String goUserInfoAct(@RequestParam("UserInfoOnlyID") Integer UserInfoOnlyID, Model model){
    UserInfo oneUserInfoByOnlyID = userInfoService.getOneUserInfoByOnlyID(UserInfoOnlyID);
    model.addAttribute("userData",oneUserInfoByOnlyID);
    return "NumberAct";
}

@PostMapping("/User/Update")
        public String updateOneUserInfo(@RequestParam("onlyID") Integer onlyID,@RequestParam("Name") String Name,@RequestParam("Email") String Email,@RequestParam("UserLevel")String UserLevel,RedirectAttributes redirectAttributes){
    Integer integer = userInfoService.updateOneUserInfoByOnlyID(Name, Email, UserLevel, onlyID);

    if(integer>0){
        redirectAttributes.addFlashAttribute("UserMsg","更改成功");
    }else {
        redirectAttributes.addFlashAttribute("UserMsg","更改失敗");
    }
    return "redirect:/User/Act?UserInfoOnlyID="+onlyID;
}

@PostMapping("/user/Delete")
    public String deleteUserInfo(@RequestParam(value = "delete",required = false) String[] ID, RedirectAttributes redirectAttributes){
    if(ID==null){

            redirectAttributes.addFlashAttribute("UserMsgDelete","沒有選擇刪除的選項，無法刪除");
            return "redirect:/NumberList";
        }
    try{
        for (String id:
        ID) {
            //刪除AccountingNote關聯資料
            Integer integer = accountingNoteSerivce.deleteAccountingNoteByUserID(id);
//刪除Categorye關聯資料
            Integer integer1 = categoryService.deleteManyCategoryByUserID(id);
//刪除CuserInfo
            Integer integer2 = userInfoService.deleteUsweInfoByID(id);
        }


    }catch (Exception exception){
        redirectAttributes.addFlashAttribute("UserMsgDelete","嚴重錯誤，無法刪除");
    }
    redirectAttributes.addFlashAttribute("UserMsgDelete","刪除成功");
    return "redirect:/NumberList";
}





}
