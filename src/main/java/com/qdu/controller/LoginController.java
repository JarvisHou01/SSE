package com.qdu.controller;

import com.qdu.pojo.User;
import com.qdu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Resource
    private UserService userService;



    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes attributes,
                        HttpSession session){


        User user = new User(username, password);

        System.out.println("提交来的用户"+user);

        User login = userService.login(user);
        System.out.println("查询到的用户"+login);

        if (login!= null){

            session.setAttribute("loginuser",username);
            session.setAttribute("uid",login.getId());

            return "redirect:/main.html";
        }else {
            attributes.addFlashAttribute("errorMsg","用户名或者密码错误");

            return "redirect:/index";
        }

    }



    @RequestMapping("/user/register")
    public String register(@RequestParam("userName") String username,
                           @RequestParam("password") String password,
                           HttpSession session){

        User newuser = new User(username, password);
        int add = userService.add(newuser);

        if (add==1){
            System.out.println("建立了新的用户" + newuser);
        }
        return "redirect:/index";

    }






    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
