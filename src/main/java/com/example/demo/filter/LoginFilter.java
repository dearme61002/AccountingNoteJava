package com.example.demo.filter;


import com.example.demo.mod.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        檢查驗面
        HttpSession loginUser = request.getSession();
        Object loginUser1 = loginUser.getAttribute("LoginUser");
        if(loginUser1 !=null){
            return true;
        }


        request.setAttribute("msg","要好好的登入喔");
        request.getRequestDispatcher("/LoginPage").forward(request,response);



        return false;
        //        檢查驗面
    }
}
