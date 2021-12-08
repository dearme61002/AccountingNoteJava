package com.example.demo.controllor;

import com.example.demo.mod.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UpateOneUserController {
    @Autowired
  UserInfoService userInfoService;
@PostMapping("/updateOneUser")
    public String updateOneUser(@RequestParam("account") String Account, @RequestParam("name") String Name, @RequestParam("email") String Email, @RequestParam("id") String ID, RedirectAttributes redirectAttributes, HttpSession httpSession){
int i=0;
    try {
     i = userInfoService.UpateOneUser(Account, Name, Email, ID);
}catch (Exception e){

        redirectAttributes.addFlashAttribute("UpateOneUser","錯誤，請正常使用本網站");
}
    if(i>0){

        UserInfo userInfo = userInfoService.getOneuserInfoByID(ID);
        redirectAttributes.addFlashAttribute("UpateOneUser","成功更改個人資訊");
        httpSession.setAttribute("LoginUser",userInfo);

    }else {

        redirectAttributes.addFlashAttribute("UpateOneUser","沒有更改個人資訊");
    }

    return "redirect:main.html";
}
}
