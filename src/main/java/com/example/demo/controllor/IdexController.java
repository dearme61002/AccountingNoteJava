package com.example.demo.controllor;

import com.example.demo.mod.IndexData;
import com.example.demo.mod.LoginUser;
import com.example.demo.mod.UserInfo;
import com.example.demo.service.IdexDataService;
import com.example.demo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.web.servlet.server.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IdexController {
@Autowired
    UserInfoService userInfoService;
@Autowired
IdexDataService idexDataService;
    /**
     *初始化頁面
     * @return
     */
    @GetMapping(value = {"/","/index.html"})
    public String indexPage(Model module){
module.addAttribute("index",idexDataService.getIndexData());

        return "index";
    }

    @GetMapping(value = "/LoginPage")
    public String loginPage(){

        return "LoginPage";
    }


    /**
     * 登出
     * @param httpSession
     * @return
     */
    @GetMapping(value = "/singOut")
    public String singOut(HttpSession httpSession){
if(httpSession ==null){

}else {
    httpSession.removeAttribute("LoginUser");

}
        return "LoginPage";
    }
    /**
     * 去燈入主頁
     * @return
     */
    @PostMapping("/login")
    public String goMain(LoginUser loginUser, HttpSession httpSession, Model module){
        if (StringUtils.hasLength(loginUser.getUserName()) && StringUtils.hasLength(loginUser.getPassword())){
            UserInfo oneuserInfo = userInfoService.getOneuserInfo(loginUser.getUserName(), loginUser.getPassword());
            if(oneuserInfo!=null){
                //success
                httpSession.setAttribute("LoginUser",oneuserInfo);
                SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                httpSession.setAttribute("loginTime",sdFormat.format(new Date()));
                return "redirect:/main.html";
            }else {
                module.addAttribute("msg","帳號密碼錯誤");
                return "LoginPage";
            }

        }else {
            module.addAttribute("msg","帳號密碼請輸入值");
            return "LoginPage";
        }


    }
    /**
     * 進入燈入主頁
     *
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession httpSession){
        //filter
        Object loginUser = httpSession.getAttribute("LoginUser");
        if(loginUser==null){
            return "LoginPage";
        }else {
            return "main";
        }
        //filter

    }
  @GetMapping("/tt")
    public String gg(){
        return "manageAdd";
    }
}
